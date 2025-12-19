package com.expensesharingapplication.service;

import com.expensesharingapplication.dtos.request.GroupRequest;
import com.expensesharingapplication.dtos.response.GroupResponse;

public interface GroupService {

    GroupResponse createGroup(GroupRequest groupRequest);

    String addMemberToGroup(String groupId, String userId);

    GroupResponse getGroupDetails(String groupId);

    String removeMemberFromGroup(String groupId, String userId);

}
