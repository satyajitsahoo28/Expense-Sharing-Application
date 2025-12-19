package com.expensesharingapplication.service;

import com.expensesharingapplication.dtos.request.ExpenseRequest;
import com.expensesharingapplication.dtos.response.ExpenseResponse;

public interface ExpenseService {

    ExpenseResponse addExpense(ExpenseRequest expenseRequest);

    ExpenseResponse getExpenseById(String expenseId);

    String deleteExpenseById(String expenseId);
}
