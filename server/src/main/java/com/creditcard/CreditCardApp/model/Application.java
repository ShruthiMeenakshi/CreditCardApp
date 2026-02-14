package com.creditcard.CreditCardApp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private int creditScore;
    private int creditLimit;
    private String rejectionReason;

    // ---------- GETTERS ----------

    public String getApplicationId() {
        return applicationId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public int getAge() {
        return age;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public String getStatus() {
        return status;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    // ---------- SETTERS ----------

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}