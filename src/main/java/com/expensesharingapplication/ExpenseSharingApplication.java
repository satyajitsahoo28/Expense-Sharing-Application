package com.expensesharingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ExpenseSharingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExpenseSharingApplication.class, args);
	}

}
