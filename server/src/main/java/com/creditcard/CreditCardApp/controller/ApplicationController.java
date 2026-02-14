package com.creditcard.CreditCardApp.controller;

import com.creditcard.CreditCardApp.model.Application;
import com.creditcard.CreditCardApp.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:5173")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public Application apply(@RequestBody Application application) {
        return applicationService.apply(application);
    }

    @GetMapping("/status/{id}")
    public Application getStatus(@PathVariable String id) {
        return applicationService.getStatus(id);
    }
}