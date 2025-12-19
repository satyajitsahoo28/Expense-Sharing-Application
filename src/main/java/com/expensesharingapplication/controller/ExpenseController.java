package com.expensesharingapplication.controller;

import com.expensesharingapplication.dtos.request.ExpenseRequest;
import com.expensesharingapplication.dtos.response.ExpenseResponse;
import com.expensesharingapplication.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/create_expense")
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest expenseRequest) {
        ExpenseResponse response = expenseService.addExpense(expenseRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/expense_details/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseDetails(@PathVariable String expenseId) {
        ExpenseResponse response = expenseService.getExpenseById(expenseId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete_expense/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable String expenseId) {
        String response = expenseService.deleteExpenseById(expenseId);
        return ResponseEntity.ok(response);
    }


}
