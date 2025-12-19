package com.expensesharingapplication.controller;

import com.expensesharingapplication.dtos.request.GroupRequest;
import com.expensesharingapplication.dtos.response.GroupResponse;
import com.expensesharingapplication.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create_group")
    public ResponseEntity<GroupResponse> createGroup(@RequestBody GroupRequest groupRequest) {
        GroupResponse groupResponse = groupService.createGroup(groupRequest);
        return ResponseEntity.ok(groupResponse);
    }

    @GetMapping("/add_members/{groupId}/{userId}")
    public ResponseEntity<String> addMembersToGroup(@PathVariable String groupId, @PathVariable String userId) {
        String response = groupService.addMemberToGroup(groupId, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/group_details/{groupId}")
    public ResponseEntity<GroupResponse> getGroupDetails(@PathVariable String groupId) {
        GroupResponse groupResponse = groupService.getGroupDetails(groupId);
        return ResponseEntity.ok(groupResponse);
    }

    @DeleteMapping("/delete_member/{groupId}/{userId}")
    public ResponseEntity<String> deleteMemberFromGroup(@PathVariable String groupId, @PathVariable String userId) {
        String response = groupService.removeMemberFromGroup(groupId, userId);
        return ResponseEntity.ok(response);
    }
}
