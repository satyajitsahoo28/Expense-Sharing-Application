package com.expensesharingapplication.factoryPattern;

import com.expensesharingapplication.entity.ExpenseSplit;

import java.util.List;

public interface Split {
    List<ExpenseSplit> calculate(double amount, List<ExpenseSplit> members);
}
