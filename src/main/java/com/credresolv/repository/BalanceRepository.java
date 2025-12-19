package com.credresolv.repository;

import com.credresolv.entity.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BalanceRepository extends MongoRepository<Balance , String> {
    List<Balance> findByGroupId(String groupId);

    List<Balance> findByFromId(String from);

    List<Balance> findByOwesToId(String owesTo);
}
