package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.SimplifiedBalance;
import com.expensesharingapplication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimplifiedBalanceRepository extends MongoRepository<SimplifiedBalance, String> {

    Optional<SimplifiedBalance> findByGroupAndFromAndTo(Group group, User from, User to);

    List<SimplifiedBalance> findByGroup(Group group);

    List<SimplifiedBalance> findByGroupAndFrom(Group group, User user);

    List<SimplifiedBalance> findByGroupAndTo(Group group, User user);
}
