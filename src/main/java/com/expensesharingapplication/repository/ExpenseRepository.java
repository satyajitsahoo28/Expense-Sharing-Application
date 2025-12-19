package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Expense;
import com.expensesharingapplication.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {

    List<Expense> findByGroup(Group group);
}
