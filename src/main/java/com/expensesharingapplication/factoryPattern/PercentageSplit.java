package com.expensesharingapplication.factoryPattern;

import com.expensesharingapplication.entity.ExpenseSplit;

import java.util.List;

public class PercentageSplit implements Split {
    @Override
    public List<ExpenseSplit> calculate(double amount, List<ExpenseSplit> members) {

        for (ExpenseSplit member : members) {
            double percentage = member.getPercentage();
            double splitAmount = (percentage / 100) * amount;
            member.setUser(member.getUser());
            member.setAmount(splitAmount);
            member.setPercentage(percentage);
        }

        return members;
    }
}
