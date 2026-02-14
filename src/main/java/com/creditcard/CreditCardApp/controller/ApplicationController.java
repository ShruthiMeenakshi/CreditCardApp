package com.creditcard.CreditCardApp.controller;

import com.creditcard.CreditCardApp.model.Application;
import com.creditcard.CreditCardApp.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @PostMapping("/apply")
    public Application apply(@Valid @RequestBody Application app) {
        return service.apply(app);
    }
}
