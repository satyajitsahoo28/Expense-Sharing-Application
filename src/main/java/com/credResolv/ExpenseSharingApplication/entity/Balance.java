package com.credResolv.ExpenseSharingApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "balances")
@CompoundIndex(def = "{'group.$id': 1, 'user.$id': 1, 'owesTo.$id': 1}", unique = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Balance {

    @Id
    private String balanceId;

    @DBRef
    private Group group;

    @DBRef
    private User user;

    @DBRef
    private User owesTo;

    private double amount;

    private LocalDateTime updatedAt;
}
