package com.expensesharingapplication.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupBalanceResponse {

    private String groupId;
    private String groupName;
    private Double totalExpenses;
    private int expensesCount;
    private List<BalanceGroupResponse> balances;
}
