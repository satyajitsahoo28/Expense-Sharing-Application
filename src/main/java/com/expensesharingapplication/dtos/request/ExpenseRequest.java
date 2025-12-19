package com.expensesharingapplication.dtos.request;

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
public class ExpenseRequest {

    private String groupId;
    private String description;
    private double totalAmount;
    private String paidBy;
    private String splitType;
    private List<ExpenseSplitRequest> splits;
}
