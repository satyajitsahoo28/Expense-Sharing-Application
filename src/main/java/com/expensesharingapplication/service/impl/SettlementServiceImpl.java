package com.expensesharingapplication.service.impl;

import com.expensesharingapplication.dtos.request.SettlementRequest;
import com.expensesharingapplication.dtos.response.GroupSettlementResponse;
import com.expensesharingapplication.dtos.response.SettlementResponse;
import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.Settlement;
import com.expensesharingapplication.entity.SimplifiedBalance;
import com.expensesharingapplication.entity.User;
import com.expensesharingapplication.exception.GroupNotFoundException;
import com.expensesharingapplication.exception.SettlementNotFoundException;
import com.expensesharingapplication.exception.SimplifiedBalanceNotFoundException;
import com.expensesharingapplication.repository.GroupRepository;
import com.expensesharingapplication.repository.SettlementRepository;
import com.expensesharingapplication.repository.SimplifiedBalanceRepository;
import com.expensesharingapplication.repository.UserRepository;
import com.expensesharingapplication.service.SettlementService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;
    private final SimplifiedBalanceRepository simplifiedBalanceRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public SettlementServiceImpl(SettlementRepository settlementRepository, SimplifiedBalanceRepository simplifiedBalanceRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.settlementRepository = settlementRepository;
        this.simplifiedBalanceRepository = simplifiedBalanceRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SettlementResponse settleUp(SettlementRequest settlementRequest) {
        Group group = groupRepository.findById(settlementRequest.getGroupId()).orElseThrow(() -> new GroupNotFoundException("Group not found"));

        User paidFrom = userRepository.findById(settlementRequest.getFromUserId()).orElseThrow(() -> new GroupNotFoundException("User not found"));
        User paidTo = userRepository.findById(settlementRequest.getToUserId()).orElseThrow(() -> new GroupNotFoundException("User not found"));

        SimplifiedBalance simplifiedBalance = simplifiedBalanceRepository.findByGroupAndFromAndTo(group, paidFrom, paidTo)
                .orElseThrow(() -> new SimplifiedBalanceNotFoundException("Simplified balance not found"));

        if(simplifiedBalance.getAmount() < settlementRequest.getAmount()) {
            throw new IllegalArgumentException("Settlement amount exceeds the owed amount");
        } else if(simplifiedBalance.getAmount() == 0){
            throw new IllegalArgumentException("No amount owed between the users");
        }

        Settlement settlement = Settlement.builder()
                .group(group)
                .paidFrom(paidFrom)
                .paidTo(paidTo)
                .amount(settlementRequest.getAmount())
                .notes(settlementRequest.getNotes())
                .build();

        Settlement saveSettlement = settlementRepository.save(settlement);

        if(simplifiedBalance.getAmount().equals(settlementRequest.getAmount())) {
            simplifiedBalanceRepository.delete(simplifiedBalance);
        } else {
            simplifiedBalance.setAmount(simplifiedBalance.getAmount() - settlementRequest.getAmount());
            simplifiedBalanceRepository.save(simplifiedBalance);
        }

        return SettlementResponse.builder()
                .settlementId(saveSettlement.getSettlementId())
                .from(saveSettlement.getPaidFrom().getName())
                .to(saveSettlement.getPaidTo().getName())
                .amount(saveSettlement.getAmount())
                .settlementDate(saveSettlement.getSettledAt())
                .notes(saveSettlement.getNotes())
                .build();
    }

    @Override
    public GroupSettlementResponse settleByGroup(String groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException("Group not found"));
        List<Settlement> settlements = settlementRepository.findByGroup(group);

        List<SettlementResponse> settlementResponses = new ArrayList<>();
        for(Settlement settlement : settlements) {
            SettlementResponse response = SettlementResponse.builder()
                    .settlementId(settlement.getSettlementId())
                    .from(settlement.getPaidFrom().getName())
                    .to(settlement.getPaidTo().getName())
                    .amount(settlement.getAmount())
                    .settlementDate(settlement.getSettledAt())
                    .notes(settlement.getNotes())
                    .build();
            settlementResponses.add(response);
        }

        return GroupSettlementResponse.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .settlements(settlementResponses)
                .build();
    }

    @Override
    public SettlementResponse findById(String settlementId) {
        Settlement settlements = settlementRepository.findById(settlementId).orElseThrow(() -> new SettlementNotFoundException("Settlement not found"));
        return SettlementResponse.builder()
                .settlementId(settlements.getSettlementId())
                .from(settlements.getPaidFrom().getName())
                .to(settlements.getPaidTo().getName())
                .amount(settlements.getAmount())
                .settlementDate(settlements.getSettledAt())
                .notes(settlements.getNotes())
                .build();


    }
}
