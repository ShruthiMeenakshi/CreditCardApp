package com.creditcard.CreditCardApp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



public record ApplicationRequest(

        @NotBlank(message = "Full name is required")
        String fullName,

        @Pattern(
                regexp = "[A-Z]{5}[0-9]{4}[A-Z]",
                message = "Invalid PAN format"
        )
        String panNumber,

        @NotNull(message = "Date of birth is required")
        LocalDate dateOfBirth,

        @Min(value = 0, message = "Annual income must be positive")
        double annualIncome
) {}



