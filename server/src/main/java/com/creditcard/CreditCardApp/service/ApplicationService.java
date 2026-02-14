package com.creditcard.CreditCardApp.service;

import com.creditcard.CreditCardApp.model.Application;
import com.creditcard.CreditCardApp.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CreditScoreService creditScoreService;

    public ApplicationService(ApplicationRepository applicationRepository,
                              CreditScoreService creditScoreService) {
        this.applicationRepository = applicationRepository;
        this.creditScoreService = creditScoreService;
    }

    // APPLY CREDIT CARD
    public Application apply(Application application) {

        int creditScore =
                creditScoreService.getCreditScore(application.getPanNumber());

        application.setCreditScore(creditScore);

        if (creditScore >= 750) {
            application.setStatus("APPROVED");
            application.setCreditLimit(500000);
        } else if (creditScore >= 650) {
            application.setStatus("APPROVED");
            application.setCreditLimit(200000);
        } else {
            application.setStatus("REJECTED");
            application.setCreditLimit(0);
            application.setRejectionReason("Low credit score");
        }

        return applicationRepository.save(application);
    }

    // ✅ GET STATUS BY ID
    public Application getStatus(String applicationId) {
        return applicationRepository.findById(applicationId)
                .orElse(null);
    }
}