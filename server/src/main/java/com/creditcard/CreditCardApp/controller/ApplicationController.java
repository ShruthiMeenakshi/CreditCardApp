package com.creditcard.CreditCardApp.controller;

import com.creditcard.CreditCardApp.dto.ApplicationRequest;
import com.creditcard.CreditCardApp.dto.ApplicationResponse;
import com.creditcard.CreditCardApp.model.ApplicationStatus;
import com.creditcard.CreditCardApp.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @PostMapping("/apply")
    public ApplicationResponse apply(
            @Valid @RequestBody ApplicationRequest request
    ) {
        return service.apply(request);
    }
    @GetMapping
    public List<ApplicationResponse> getAllApplications() {
        return service.getAllApplications();
    }
    // 2️⃣ GET BY ID
    @GetMapping("/{id}")
    public ApplicationResponse getById(@PathVariable String id) {
        return service.getApplicationById(id);
    }
    @GetMapping("/status/{status}")
    public List<ApplicationResponse> getByStatus(@PathVariable ApplicationStatus status) {
        return service.getApplicationsByStatus(status);
    }


}
