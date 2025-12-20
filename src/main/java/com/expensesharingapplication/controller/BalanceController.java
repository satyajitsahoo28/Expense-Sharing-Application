package com.expensesharingapplication.controller;

import com.expensesharingapplication.dtos.response.GroupBalanceResponse;
import com.expensesharingapplication.dtos.response.UserBalanceResponse;
import com.expensesharingapplication.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/group_balances/{groupId}")
    public ResponseEntity<GroupBalanceResponse> getGroupBalances(@PathVariable String groupId) {
        GroupBalanceResponse response = balanceService.getBalancesForGroup(groupId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user_balances/{userId}/{groupId}")
    public ResponseEntity<UserBalanceResponse> getUserBalancesInGroup(@PathVariable String userId, @PathVariable String groupId) {
        UserBalanceResponse response = balanceService.getBalancesForUser(userId, groupId);
        return ResponseEntity.ok(response);
    }
}
