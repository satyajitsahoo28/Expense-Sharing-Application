package com.expensesharingapplication.factoryPattern;

import com.expensesharingapplication.enums.SplitType;

public class SplitFactory {

    public static Split getSplitType(SplitType type) {
        if(type.equals(SplitType.EQUAL)) {
            return new EqualSplit();
        } else if(type.equals(SplitType.EXACT)) {
            return new ExactAmountSplit();
        } else if(type.equals(SplitType.PERCENTAGE)) {
            return new PercentageSplit();
        } else {
            throw new IllegalArgumentException("Invalid split type: " + type);
        }
    }
}
