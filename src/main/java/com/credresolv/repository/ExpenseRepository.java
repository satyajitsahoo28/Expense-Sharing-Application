package com.credresolv.repository;

import com.credresolv.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByGroupId(String groupId);
    List<Expense> findByPaidById(String paidBy);
}
