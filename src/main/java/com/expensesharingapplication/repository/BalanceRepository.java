package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Balance;
import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends MongoRepository<Balance , String> {

    List<Balance> findByGroup(Group group);

}
