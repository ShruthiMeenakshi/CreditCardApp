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

    public Application apply(Application application) {

        // Step 1: Get credit score
        int creditScore = creditScoreService
                .getCreditScore(application.getPanNumber());

        application.setCreditScore(creditScore);

        // Step 2: Decision logic
        if (creditScore >= 750) {
            application.setStatus("APPROVED");
            application.setCreditLimit(500000);
        }
        else if (creditScore >= 650) {
            application.setStatus("APPROVED");
            application.setCreditLimit(200000);
        }
        else {
            application.setStatus("REJECTED");
            application.setCreditLimit(0);
            application.setRejectionReason("Low credit score");
        }

        // Step 3: Save and return
        return applicationRepository.save(application);
    }
}
