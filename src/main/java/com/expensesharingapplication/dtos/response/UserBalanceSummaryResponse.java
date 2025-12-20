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
public class UserBalanceSummaryResponse {

    private String userId;

    private String name;

    private Double totalOwed;

    private Double totalOwing;

    private Double netBalance;

    private List<UserBalanceDetailsResponse> detailsResponseList;
}
