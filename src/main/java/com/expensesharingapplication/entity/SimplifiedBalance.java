package com.expensesharingapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "simplified_balances")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimplifiedBalance {

    @Id
    private String simplifiedBalanceId;

    @DBRef
    private Group group;

    @DBRef
    private User from;

    @DBRef
    private User to;

    private Double amount;
}
