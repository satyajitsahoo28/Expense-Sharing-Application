package com.expensesharingapplication.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceGroupResponse {

    private String from;
    private String to;
    private Double amount;
}
