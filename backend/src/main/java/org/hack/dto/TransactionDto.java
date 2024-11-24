package org.hack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long transactionId;
    private Long userId;
    private BigDecimal amount;
    private String transactionType;    // 'income' / 'expense'
    private Date transactionDate;
    private String category;
}
