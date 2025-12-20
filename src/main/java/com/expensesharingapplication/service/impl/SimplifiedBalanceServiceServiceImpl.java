package com.expensesharingapplication.service.impl;

import com.expensesharingapplication.dtos.response.BalanceResponse;
import com.expensesharingapplication.dtos.response.SimplifiedBalanceDetails;
import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.SimplifiedBalance;
import com.expensesharingapplication.exception.GroupNotFoundException;
import com.expensesharingapplication.repository.GroupRepository;
import com.expensesharingapplication.repository.SimplifiedBalanceRepository;
import com.expensesharingapplication.service.SimplifiedBalanceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimplifiedBalanceServiceServiceImpl implements SimplifiedBalanceService {

    private final SimplifiedBalanceRepository simplifiedBalanceRepository;
    private final GroupRepository groupRepository;

    public SimplifiedBalanceServiceServiceImpl(SimplifiedBalanceRepository simplifiedBalanceRepository, GroupRepository groupRepository) {
        this.simplifiedBalanceRepository = simplifiedBalanceRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public SimplifiedBalanceDetails getSimplifiedBalance(String groupId) {

        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException("Group not found"));

        List<BalanceResponse> balanceResponseList = new ArrayList<>();

        List<SimplifiedBalance> byGroup = simplifiedBalanceRepository.findByGroup(group);

        for(SimplifiedBalance simplifiedBalance : byGroup) {
            BalanceResponse balanceResponse = BalanceResponse.builder()
                    .from(simplifiedBalance.getFrom().getName())
                    .to(simplifiedBalance.getTo().getName())
                    .amount(simplifiedBalance.getAmount())
                    .build();
            balanceResponseList.add(balanceResponse);
        }

        return SimplifiedBalanceDetails.builder()
                .groupId(groupId)
                .groupName(group.getGroupName())
                .simplifiedBalancesDetails(balanceResponseList)
                .build();
    }
}
