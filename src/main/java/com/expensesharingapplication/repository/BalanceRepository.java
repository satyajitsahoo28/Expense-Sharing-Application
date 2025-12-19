package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Balance;
import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository extends MongoRepository<Balance , String> {

    List<Balance> findByFromOrOwesTo(User from, User owesTo);

    List<Balance> findByGroup(Group group);

    Optional<Balance> findByGroupAndFromAndOwesTo(Group group, User from, User owesTo);
}
