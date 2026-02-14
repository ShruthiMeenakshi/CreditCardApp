package com.creditcard.CreditCardApp.controller;

import com.creditcard.CreditCardApp.dto.CreditScoreResponse;
import com.creditcard.CreditCardApp.service.CreditScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit-score")
@CrossOrigin
public class CreditScoreController {

    private final CreditScoreService creditScoreService;

    public CreditScoreController(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    @GetMapping("/{pan}")
    public CreditScoreResponse getCreditScore(@PathVariable String pan) {

        int score = creditScoreService.getCreditScore(pan);
        return new CreditScoreResponse(pan, score);
    }
}
