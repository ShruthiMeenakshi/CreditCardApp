package com.creditcard.CreditCardApp.dto;

public record ApplicationResponse(String applicationId, com.creditcard.CreditCardApp.model.ApplicationStatus status, Integer creditLimit, String rejectionReason) {
}
