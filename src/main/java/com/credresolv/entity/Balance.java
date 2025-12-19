package com.credresolv.entity;

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
    private User from;

    @DBRef
    private User owesTo;

    private double amount;

    private LocalDateTime updatedAt = LocalDateTime.now();
}
