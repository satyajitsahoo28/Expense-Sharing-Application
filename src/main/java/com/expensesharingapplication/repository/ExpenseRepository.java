package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Expense;
import com.expensesharingapplication.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {

    List<Expense> findByGroup(Group group);
}
