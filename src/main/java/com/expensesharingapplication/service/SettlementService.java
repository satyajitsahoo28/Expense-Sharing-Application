package com.expensesharingapplication.service;

import com.expensesharingapplication.dtos.request.SettlementRequest;
import com.expensesharingapplication.dtos.response.GroupSettlementResponse;
import com.expensesharingapplication.dtos.response.SettlementResponse;
import com.expensesharingapplication.entity.Settlement;

import java.util.List;

public interface SettlementService {

    SettlementResponse settleUp(SettlementRequest settlementRequest);

    GroupSettlementResponse settleByGroup(String groupId);

    SettlementResponse findById(String settlementId);
}
