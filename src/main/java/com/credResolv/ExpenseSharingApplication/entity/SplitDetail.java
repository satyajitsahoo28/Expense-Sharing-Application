package com.credResolv.ExpenseSharingApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SplitDetail {

    @DBRef
    private User user;

    private double amount;

    private double percentage;
}
