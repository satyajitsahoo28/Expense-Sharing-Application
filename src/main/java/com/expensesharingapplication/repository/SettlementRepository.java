package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Settlement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementRepository extends MongoRepository<Settlement , String> {

}
