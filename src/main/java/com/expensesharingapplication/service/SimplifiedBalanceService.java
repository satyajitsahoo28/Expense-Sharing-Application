package com.expensesharingapplication.service;


import com.expensesharingapplication.dtos.response.SimplifiedBalanceDetails;

public interface SimplifiedBalanceService {

    SimplifiedBalanceDetails getSimplifiedBalance(String groupId);
}
