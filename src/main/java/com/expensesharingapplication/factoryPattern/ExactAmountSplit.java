package com.expensesharingapplication.factoryPattern;

import com.expensesharingapplication.entity.ExpenseSplit;

import java.util.List;

public class ExactAmountSplit implements Split {
    @Override
    public List<ExpenseSplit> calculate(double amount, List<ExpenseSplit> members) {

        return members;
    }
}
