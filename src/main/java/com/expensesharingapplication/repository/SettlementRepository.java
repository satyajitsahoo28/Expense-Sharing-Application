package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.Settlement;
import com.expensesharingapplication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SettlementRepository extends MongoRepository<Settlement , String> {

    List<Settlement> findByGroup(Group group);

    List<Settlement> findByPaidFromOrPaidTo(User from, User to);
}
