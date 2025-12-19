package com.expensesharingapplication.dtos.response;

import com.expensesharingapplication.entity.ExpenseSplit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse {

    private String expenseId;
    private String description;
    private double totalAmount;
    private List<ExpenseSplit> splits;
}
