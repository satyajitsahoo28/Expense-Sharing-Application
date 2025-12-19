package com.expensesharingapplication.service.impl;

import com.expensesharingapplication.dtos.request.ExpenseRequest;
import com.expensesharingapplication.dtos.request.ExpenseSplitRequest;
import com.expensesharingapplication.dtos.response.ExpenseResponse;
import com.expensesharingapplication.entity.Expense;
import com.expensesharingapplication.entity.ExpenseSplit;
import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.User;
import com.expensesharingapplication.enums.SplitType;
import com.expensesharingapplication.exception.ExpenseNotFoundException;
import com.expensesharingapplication.exception.GroupNotFoundException;
import com.expensesharingapplication.exception.UserNotFoundException;
import com.expensesharingapplication.factoryPattern.Split;
import com.expensesharingapplication.factoryPattern.SplitFactory;
import com.expensesharingapplication.repository.ExpenseRepository;
import com.expensesharingapplication.repository.GroupRepository;
import com.expensesharingapplication.repository.UserRepository;
import com.expensesharingapplication.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExpenseResponse addExpense(ExpenseRequest expenseRequest) {

        Group group = groupRepository.findById(expenseRequest.getGroupId()).orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + expenseRequest.getGroupId()));
        User user = userRepository.findById(expenseRequest.getPaidBy()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + expenseRequest.getPaidBy()));

        SplitType splitType = SplitType.valueOf(expenseRequest.getSplitType().toUpperCase());

        Expense expense = Expense.builder()
                .group(group)
                .description(expenseRequest.getDescription())
                .totalAmount(expenseRequest.getTotalAmount())
                .paidBy(user)
                .splitType(splitType)
                .build();


        List<ExpenseSplit> expenseSplits = new ArrayList<>();
        for (ExpenseSplitRequest request: expenseRequest.getSplits()) {
            User userExpenses = userRepository.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUserId()));
            ExpenseSplit expenseSplit = ExpenseSplit.builder()
                    .user(userExpenses)
                    .amount(request.getAmount())
                    .percentage(request.getPercentage())
                    .build();
            expenseSplits.add(expenseSplit);
        }

        Split split = SplitFactory.getSplitType(splitType);
        List<ExpenseSplit> calculateSplits = split.calculate(expenseRequest.getTotalAmount(), expenseSplits);
        expense.setSplits(calculateSplits);

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseResponse.builder()
                .expenseId(savedExpense.getExpenseId())
                .description(savedExpense.getDescription())
                .totalAmount(savedExpense.getTotalAmount())
                .splits(savedExpense.getSplits())
                .build();

    }

    @Override
    public ExpenseResponse getExpenseById(String expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + expenseId));
        return ExpenseResponse.builder()
                .expenseId(expense.getExpenseId())
                .description(expense.getDescription())
                .totalAmount(expense.getTotalAmount())
                .splits(expense.getSplits())
                .build();
    }

    @Override
    public String deleteExpenseById(String expenseId) {
        expenseRepository.deleteById(expenseId);
        return "Expense deleted successfully";
    }
}
