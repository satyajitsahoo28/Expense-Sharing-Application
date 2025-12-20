package com.expensesharingapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "settlements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Settlement {

    @Id
    private String settlementId;

    @DBRef
    private Group group;

    @DBRef
    private User paidFrom;

    @DBRef
    private User paidTo;

    private Double amount;

    @Builder.Default
    private LocalDateTime settledAt = LocalDateTime.now();

    private String notes;
}
