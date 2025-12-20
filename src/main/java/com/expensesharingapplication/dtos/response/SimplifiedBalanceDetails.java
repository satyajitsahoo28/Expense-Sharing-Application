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
public class SimplifiedBalanceDetails {

    private String groupId;
    private String groupName;
    private List<BalanceResponse> simplifiedBalancesDetails;
}
