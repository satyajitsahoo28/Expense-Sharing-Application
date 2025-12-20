package com.expensesharingapplication.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public String handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public String handleGroupNotFoundException(GroupNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SettlementNotFoundException.class)
    public String handleSettlementNotFoundException(SettlementNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SimplifiedBalanceNotFoundException.class)
    public String handleSimplifiedBalanceNotFoundException(SimplifiedBalanceNotFoundException ex) {
        return ex.getMessage();
    }
}
