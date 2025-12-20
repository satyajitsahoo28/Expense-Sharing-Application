package com.expensesharingapplication.service.impl;

import com.expensesharingapplication.dtos.response.BalanceGroupResponse;
import com.expensesharingapplication.dtos.response.GroupBalanceResponse;
import com.expensesharingapplication.dtos.response.SimplifiedUserBalanceDetails;
import com.expensesharingapplication.dtos.response.UserBalanceResponse;
import com.expensesharingapplication.entity.*;
import com.expensesharingapplication.exception.GroupNotFoundException;
import com.expensesharingapplication.exception.UserNotFoundException;
import com.expensesharingapplication.repository.*;
import com.expensesharingapplication.service.BalanceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final SimplifiedBalanceRepository simplifiedBalanceRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository, SimplifiedBalanceRepository simplifiedBalanceRepository, GroupRepository groupRepository, ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.balanceRepository = balanceRepository;
        this.simplifiedBalanceRepository = simplifiedBalanceRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBalancesForExpense(User paidUser, Group group, List<ExpenseSplit> expenseSplits) {

        for(ExpenseSplit split: expenseSplits) {

            if(split.getUser().getUserId().equals(paidUser.getUserId())){
                continue;
            }

            User splitUser = split.getUser();
            double splitAmount = split.getAmount();

            Balance balance = Balance.builder()
                    .group(group)
                    .from(splitUser)
                    .to(paidUser)
                    .amount(splitAmount)
                    .build();
            balanceRepository.save(balance);

            updateSimplifiedBalance(group, splitUser, paidUser, splitAmount);

        }
    }

    private void updateSimplifiedBalance(Group group, User splitUser, User paidUser, double splitAmount) {

        Optional<SimplifiedBalance> directPath = simplifiedBalanceRepository.findByGroupAndFromAndTo(group, splitUser, paidUser);

        Optional<SimplifiedBalance> reversePath = simplifiedBalanceRepository.findByGroupAndFromAndTo(group, paidUser, splitUser);

        if(directPath.isPresent()) {
            SimplifiedBalance simplifiedBalance = directPath.get();
            simplifiedBalance.setAmount(simplifiedBalance.getAmount() + splitAmount);
            simplifiedBalanceRepository.save(simplifiedBalance);
        } else if (reversePath.isPresent()) {

            SimplifiedBalance simplifiedBalance = reversePath.get();
            double remainingAmount = simplifiedBalance.getAmount() - splitAmount;

            if(remainingAmount > 0){
                simplifiedBalance.setAmount(remainingAmount);
                simplifiedBalanceRepository.save(simplifiedBalance);
            } else if (remainingAmount < 0) {

                simplifiedBalanceRepository.delete(simplifiedBalance);
                SimplifiedBalance newSimplifiedBalance = SimplifiedBalance.builder()
                        .group(group)
                        .from(splitUser)
                        .to(paidUser)
                        .amount(Math.abs(remainingAmount))
                        .build();
                simplifiedBalanceRepository.save(newSimplifiedBalance);

            }else{
                simplifiedBalanceRepository.delete(simplifiedBalance);
            }

        } else{
            SimplifiedBalance newSimplifiedBalance = SimplifiedBalance.builder()
                    .group(group)
                    .from(splitUser)
                    .to(paidUser)
                    .amount(splitAmount)
                    .build();
            simplifiedBalanceRepository.save(newSimplifiedBalance);
        }
    }

    @Override
    public GroupBalanceResponse getBalancesForGroup(String groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + groupId));

        double totalExpenses = 0.0;
        List<Expense> expenses = expenseRepository.findByGroup(group);
        for(Expense expense: expenses) {
            totalExpenses += expense.getTotalAmount();
        }
        int expenseCount = expenses.size();
        List<Balance> balances = balanceRepository.findByGroup(group);
        List<BalanceGroupResponse> balanceGroupResponses = new ArrayList<>();
        for(Balance balance : balances) {
            User fromUser = userRepository.findById(balance.getFrom().getUserId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + balance.getFrom().getUserId()));
            User toUser = userRepository.findById(balance.getTo().getUserId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + balance.getTo().getUserId()));

            BalanceGroupResponse balanceGroupResponse = BalanceGroupResponse.builder()
                    .from(fromUser.getName())
                    .to(toUser.getName())
                    .amount(balance.getAmount())
                    .build();
            balanceGroupResponses.add(balanceGroupResponse);
        }
        return GroupBalanceResponse.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .totalExpenses(totalExpenses)
                .expensesCount(expenseCount)
                .balances(balanceGroupResponses)
                .build();

    }

    @Override
    public UserBalanceResponse getBalancesForUser(String userId, String groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + groupId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        double amountUserOwes = 0.0;
        double amountOthersOweUser = 0.0;

        List<SimplifiedBalance> userOwesList = simplifiedBalanceRepository.findByGroupAndFrom(group, user);
        List<SimplifiedBalance> othersOweList = simplifiedBalanceRepository.findByGroupAndTo(group, user);

        List<SimplifiedUserBalanceDetails> userOwesToMembers = new ArrayList<>();
        List<SimplifiedUserBalanceDetails> membersOweUser = new ArrayList<>();

        for(SimplifiedBalance simplifiedBalance: userOwesList) {
            amountUserOwes += simplifiedBalance.getAmount();
            SimplifiedUserBalanceDetails simplifiedUserBalanceDetails = SimplifiedUserBalanceDetails.builder()
                    .name(simplifiedBalance.getTo().getName())
                    .amount(simplifiedBalance.getAmount())
                    .status(user.getName() + " owes ₹" + simplifiedBalance.getAmount() + " to " + simplifiedBalance.getTo().getName())
                    .build();
            userOwesToMembers.add(simplifiedUserBalanceDetails);
        }

        for(SimplifiedBalance simplifiedBalance: othersOweList) {
            amountOthersOweUser += simplifiedBalance.getAmount();
            SimplifiedUserBalanceDetails simplifiedUserBalanceDetails = SimplifiedUserBalanceDetails.builder()
                    .name(simplifiedBalance.getFrom().getName())
                    .amount(simplifiedBalance.getAmount())
                    .status(simplifiedBalance.getFrom().getName() + " owes ₹" + simplifiedBalance.getAmount() + " to " + user.getName())
                    .build();
            membersOweUser.add(simplifiedUserBalanceDetails);
        }

        double netBalance = amountOthersOweUser - amountUserOwes;

        String description = netBalance > 0 ? user.getName() + " will receive ₹" + netBalance:
                user.getName() + " needs to pay ₹" + Math.abs(netBalance);

        return UserBalanceResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .amountUserOwes(amountUserOwes)
                .amountOthersOweUser(amountOthersOweUser)
                .netBalance(netBalance)
                .description(description)
                .userOwesToMembers(userOwesToMembers)
                .membersOweUser(membersOweUser)
                .build();

    }
}
