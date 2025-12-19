package com.expensesharingapplication.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseSplitRequest {

    private String userId;

    private Double amount;

    private Double percentage;
}
