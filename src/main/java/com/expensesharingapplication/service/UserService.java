package com.expensesharingapplication.service;


import com.expensesharingapplication.dtos.request.UserRequest;
import com.expensesharingapplication.dtos.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(String userId);

}
