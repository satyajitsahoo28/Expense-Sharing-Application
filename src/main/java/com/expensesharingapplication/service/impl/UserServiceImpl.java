package com.expensesharingapplication.service.impl;

import com.expensesharingapplication.dtos.request.UserRequest;
import com.expensesharingapplication.dtos.response.UserResponse;
import com.expensesharingapplication.entity.User;
import com.expensesharingapplication.exception.EmailAlreadyExists;
import com.expensesharingapplication.exception.UserNotFound;
import com.expensesharingapplication.repository.UserRepository;
import com.expensesharingapplication.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse createUser(UserRequest userRequest) {

        boolean userExists = userRepository.existsByEmail(userRequest.getEmail());
        if(userExists) {
            throw new EmailAlreadyExists("Email already registered: " + userRequest.getEmail());
        }
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getUserId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public UserResponse getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound("User not found with id: " + userId));
        return UserResponse.builder()
                .id(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
