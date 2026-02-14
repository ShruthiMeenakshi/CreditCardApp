package com.creditcard.CreditCardApp.dto;

public class CreditScoreResponse {

    private String panNumber;
    private int creditScore;

    public CreditScoreResponse(String panNumber, int creditScore) {
        this.panNumber = panNumber;
        this.creditScore = creditScore;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public int getCreditScore() {
        return creditScore;
    }
}
