package com.expensesharingapplication.dtos.response;

import com.expensesharingapplication.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupResponse {

    private String groupId;
    private String groupName;
    private String createdBy;
    private List<User> members;
    private LocalDateTime createdAt;
}
