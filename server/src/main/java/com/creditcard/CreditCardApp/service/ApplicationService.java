package com.creditcard.CreditCardApp.service;

import com.creditcard.CreditCardApp.dto.ApplicationRequest;
import com.creditcard.CreditCardApp.dto.ApplicationResponse;
import com.creditcard.CreditCardApp.model.Application;
import com.creditcard.CreditCardApp.model.ApplicationStatus;
import com.creditcard.CreditCardApp.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        application.setAppliedDate(LocalDate.now().atStartOfDay());

        // Step 1: Get credit score
        int creditScore = creditScoreService.getCreditScore(request.panNumber());
        application.setCreditScore(creditScore);

        // Step 2: Decision logic
        if (creditScore >= 750) {
            application.setStatus(ApplicationStatus.APPROVED);
            application.setCreditLimit(500_000);
        } else if (creditScore >= 650) {
            application.setStatus(ApplicationStatus.APPROVED);
            application.setCreditLimit(200_000);
        } else {
            application.setStatus(ApplicationStatus.REJECTED);
            application.setCreditLimit(0);
            application.setRejectionReason("Low credit score");
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
}
