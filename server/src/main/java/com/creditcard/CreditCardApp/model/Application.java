package com.creditcard.CreditCardApp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "applications")
public class Application {

    @Id
    private String applicationId;

    private String fullName;

    // PAN number (can be encrypted later)
    private String panNumber;

    private LocalDate dateOfBirth;

    private double annualIncome;

    private Integer creditScore;
    private Integer creditLimit;

    private ApplicationStatus status;

    private String rejectionReason;

    private LocalDateTime appliedDate;
}
