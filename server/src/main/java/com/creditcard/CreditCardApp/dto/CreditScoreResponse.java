package com.creditcard.CreditCardApp.dto;

import java.time.LocalDate;

public record CreditScoreResponse(String applicantName, LocalDate dateOfBirth, String panNumber, Double annualIncome) {

}

