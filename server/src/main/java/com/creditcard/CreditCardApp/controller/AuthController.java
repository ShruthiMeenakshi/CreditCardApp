package com.creditcard.CreditCardApp.controller;

import com.creditcard.CreditCardApp.dto.LoginRequest;
import com.creditcard.CreditCardApp.dto.LoginResponse;
import com.creditcard.CreditCardApp.service.AuthService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Nonnull @Valid @RequestBody LoginRequest request) {

        String token = authService.login(
                request.username(),
                request.password()
        );

        return new LoginResponse(token);
    }
}
