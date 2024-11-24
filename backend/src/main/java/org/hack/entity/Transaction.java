package org.hack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "transaction_type", nullable = false)
    private String transactionType;    // 'income' / 'expense'
    @Column(name = "transaction_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Column(name = "category", nullable = false)
    private String category;
}
