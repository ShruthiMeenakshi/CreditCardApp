package com.creditcard.CreditCardApp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "applications")
public class Application {
    @Id
    private String applicationId;

    @NotBlank
    private String fullName;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]")
    private String panNumber;

    @Min(18)
    private int age;

    @Min(0)
    private double annualIncome;

    private String status;
    private int creditScore;     // ✅ int (matches service)
    private int creditLimit;     // ✅ int
    private String rejectionReason;

}
