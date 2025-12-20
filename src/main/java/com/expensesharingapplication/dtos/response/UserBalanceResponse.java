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
public class UserBalanceResponse {

    private String userId;
    private String name;
    private Double amountUserOwes;
    private Double amountOthersOweUser;
    private Double netBalance;
    private String description;
    private List<SimplifiedUserBalanceDetails> userOwesToMembers;
    private List<SimplifiedUserBalanceDetails> membersOweUser;
}
