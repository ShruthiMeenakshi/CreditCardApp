package com.creditcard.CreditCardApp.service;

import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {

    public int getCreditScore(String panNumber) {

        // Simple PAN validation
        if (panNumber == null || panNumber.length() != 10) {
            throw new IllegalArgumentException("Invalid PAN number");
        }

        /*
         * Deterministic mock logic:
         * - Convert PAN characters to numeric value
         * - Keep score in range 300–900
         */
        int hash = Math.abs(panNumber.hashCode());

        return 300 + (hash % 601); // 300 to 900
    }
}
