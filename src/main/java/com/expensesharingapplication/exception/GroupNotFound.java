package com.expensesharingapplication.exception;

public class GroupNotFound extends RuntimeException {
    public GroupNotFound(String message) {
        super(message);
    }
}
