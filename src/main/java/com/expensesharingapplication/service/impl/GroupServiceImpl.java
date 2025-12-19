package com.expensesharingapplication.service.impl;

import com.expensesharingapplication.dtos.request.GroupRequest;
import com.expensesharingapplication.dtos.response.GroupResponse;
import com.expensesharingapplication.entity.Group;
import com.expensesharingapplication.entity.User;
import com.expensesharingapplication.exception.GroupNotFound;
import com.expensesharingapplication.exception.UserNotFound;
import com.expensesharingapplication.repository.GroupRepository;
import com.expensesharingapplication.repository.UserRepository;
import com.expensesharingapplication.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GroupResponse createGroup(GroupRequest groupRequest) {

        User user = userRepository.findById(groupRequest.getCreatedBy()).orElseThrow(() -> new UserNotFound("User not found"));
        Group group = Group.builder()
                .groupName(groupRequest.getGroupName())
                .description(groupRequest.getDescription())
                .createdBy(user)
                .build();
        group.getGroupMembers().add(user);
        Group savedGroup = groupRepository.save(group);

        return GroupResponse.builder()
                .groupId(savedGroup.getGroupId())
                .groupName(savedGroup.getGroupName())
                .createdBy(savedGroup.getCreatedBy().getUserId())
                .members(savedGroup.getGroupMembers())
                .createdAt(savedGroup.getCreatedAt())
                .build();
    }

    @Override
    public String addMemberToGroup(String groupId, String userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFound("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound("User not found"));

        group.getGroupMembers().add(user);
        groupRepository.save(group);

        return user.getName() + " added to group " + group.getGroupName() + " successfully.";
    }

    @Override
    public GroupResponse getGroupDetails(String groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFound("Group not found"));
        return GroupResponse.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .createdBy(group.getCreatedBy().getUserId())
                .members(group.getGroupMembers())
                .createdAt(group.getCreatedAt())
                .build();
    }
}
