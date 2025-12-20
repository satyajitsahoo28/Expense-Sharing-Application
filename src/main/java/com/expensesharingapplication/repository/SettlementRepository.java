package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.Settlement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends MongoRepository<Settlement , String> {

    List<Settlement> findByGroup(Group group);

}
