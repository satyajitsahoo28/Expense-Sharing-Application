package com.credresolv.repository;

import com.credresolv.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {
    List<Group> findByCreatedById(String userId);


}
