package com.expensesharingapplication.factoryPattern;

import com.expensesharingapplication.entity.ExpenseSplit;

import java.util.List;

public class EqualSplit implements Split {

    @Override
    public List<ExpenseSplit> calculate(double amount, List<ExpenseSplit> members) {

        int totalMembers = members.size();
        double splitAmount = amount / totalMembers;

        for(ExpenseSplit member : members){
            member.setUser(member.getUser());
            member.setAmount(splitAmount);
        }

        return members;
    }
}
