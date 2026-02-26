package com.creditcard.CreditCardApp.service;

import com.creditcard.CreditCardApp.dto.ApplicationRequest;
import com.creditcard.CreditCardApp.dto.ApplicationResponse;
import com.creditcard.CreditCardApp.model.Application;
import com.creditcard.CreditCardApp.model.ApplicationStatus;
import com.creditcard.CreditCardApp.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CreditScoreService creditScoreService;

    public ApplicationService(ApplicationRepository applicationRepository,
                              CreditScoreService creditScoreService) {
        this.applicationRepository = applicationRepository;
        this.creditScoreService = creditScoreService;
    }

    public ApplicationResponse apply(ApplicationRequest request) {

        // Step 0: DTO → Entity
        Application application = new Application();
        application.setFullName(request.fullName());
        application.setPanNumber(request.panNumber());
        application.setDateOfBirth(request.dateOfBirth());
        application.setAnnualIncome(request.annualIncome());
        application.setAppliedDate(LocalDate.now().atStartOfDay());

        // Step 1: Get credit score (store for records; not the sole approval criteria)
        int creditScore = creditScoreService.getCreditScore(request.panNumber());
        application.setCreditScore(creditScore);

        // Step 2: Age validation - applicant must be older than 18
        int age = Period.between(request.dateOfBirth(), LocalDate.now()).getYears();
        if (age <= 18) {
            application.setStatus(ApplicationStatus.REJECTED);
            application.setCreditLimit(0);
            application.setRejectionReason("Applicant must be older than 18");
            Application savedApp = applicationRepository.save(application);
            return new ApplicationResponse(
                    savedApp.getApplicationId(),
                    savedApp.getStatus(),
                    savedApp.getCreditLimit(),
                    savedApp.getRejectionReason()
            );
        }

        // Step 3: Income-based credit limit rules
        double income = request.annualIncome();
        if (income <= 200_000) {
            application.setStatus(ApplicationStatus.APPROVED);
            application.setCreditLimit(50_000);
        } else if (income <= 300_000) {
            application.setStatus(ApplicationStatus.APPROVED);
            application.setCreditLimit(75_000);
        } else if (income <= 500_000) {
            application.setStatus(ApplicationStatus.APPROVED);
            application.setCreditLimit(1_000_000);
        } else {
            application.setStatus(ApplicationStatus.PENDING);
            application.setCreditLimit(null);
            application.setRejectionReason("Manual review required for high income applicants");
        }

        // Step 3: Save to MongoDB
        Application savedApplication = applicationRepository.save(application);

        // Step 4: Entity → Response DTO
        return new ApplicationResponse(
                savedApplication.getApplicationId(),
                savedApplication.getStatus(),
                savedApplication.getCreditLimit(),
                savedApplication.getRejectionReason()
        );

    }
    public List<ApplicationResponse> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();

        return applications.stream()
                .map(app -> new ApplicationResponse(
                        app.getApplicationId(),
                        app.getStatus(),
                        app.getCreditLimit(),
                        app.getRejectionReason()
                ))
                .toList();
    }

    public ApplicationResponse getApplicationById(String id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        return new ApplicationResponse(
                application.getApplicationId(),   // or getId() if your field is named id
                application.getStatus(),
                application.getCreditLimit(),
                application.getRejectionReason()
        );
    }
    public List<ApplicationResponse> getApplicationsByStatus(ApplicationStatus status) {

        List<Application> applications = applicationRepository.findByStatus(status);

        return applications.stream()
                .map(app -> new ApplicationResponse(
                        app.getApplicationId(),   // or getId() depending on your field
                        app.getStatus(),
                        app.getCreditLimit(),
                        app.getRejectionReason()
                ))
                .toList();
    }

}
