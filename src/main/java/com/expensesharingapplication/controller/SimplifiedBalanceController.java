package com.expensesharingapplication.controller;

import com.expensesharingapplication.dtos.response.SimplifiedBalanceDetails;
import com.expensesharingapplication.service.SimplifiedBalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("simplified-balance")
public class SimplifiedBalanceController {

    private final SimplifiedBalanceService simplifiedBalanceService;

    public SimplifiedBalanceController(SimplifiedBalanceService simplifiedBalanceService) {
        this.simplifiedBalanceService = simplifiedBalanceService;
    }

    @GetMapping("simplified-balance-details/{groupId}")
    public ResponseEntity<SimplifiedBalanceDetails> getSimplifiedBalance(@PathVariable String groupId) {
        SimplifiedBalanceDetails simplifiedBalanceDetails = simplifiedBalanceService.getSimplifiedBalance(groupId);
        return ResponseEntity.ok(simplifiedBalanceDetails);
    }
}
