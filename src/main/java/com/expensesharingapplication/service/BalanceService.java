package com.expensesharingapplication.service;

import com.expensesharingapplication.dtos.response.GroupBalanceResponse;
import com.expensesharingapplication.dtos.response.UserBalanceResponse;
import com.expensesharingapplication.entity.ExpenseSplit;
import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.User;

import java.util.List;

public interface BalanceService {

    void createBalancesForExpense(User user, Group group, List<ExpenseSplit> expenseSplits);

    GroupBalanceResponse getBalancesForGroup(String groupId);

    UserBalanceResponse getBalancesForUser(String userId, String groupId);
}
