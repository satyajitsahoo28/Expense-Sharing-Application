package com.expensesharingapplication.service;

import com.expensesharingapplication.entity.ExpenseSplit;

import java.util.List;

public interface BalanceService {

    void createBalancesForExpense(String userId, List<ExpenseSplit> expenseSplits);


}
