package com.credresolv.repository;

import com.credresolv.entity.Settlement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SettlementRepository extends MongoRepository<Settlement , String> {
    List<Settlement> findByGroupId(String groupId);

    List<Settlement> findByPaidFromId(String paidFrom);

    List<Settlement> findByPaidToId(String paidTo);
}
