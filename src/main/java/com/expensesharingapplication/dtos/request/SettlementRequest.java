package com.expensesharingapplication.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettlementRequest {

    private String groupId;
    private String fromUserId;
    private String toUserId;
    private Double amount;
    private String notes;
}
