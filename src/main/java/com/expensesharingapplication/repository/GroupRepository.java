package com.expensesharingapplication.repository;

import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {

    List<Group> findByCreatedBy(User user);

    List<Group> findByGroupMembersContains(User user);
}
