package com.credResolv.ExpenseSharingApplication.entity;


import com.credResolv.ExpenseSharingApplication.enums.SplitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    private String expenseId;

    @DBRef
    private Group group;

    private String description;

    private double totalAmount;

    @DBRef
    private User paidBy;

    private SplitType splitType;

    private List<SplitDetail> splits = new ArrayList<>();

    private LocalDateTime createdAt;
}
