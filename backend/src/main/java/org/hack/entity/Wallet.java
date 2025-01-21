package org.hack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "wallets", schema = "public", catalog = "tbank_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "wallet_id")
    private Long walletId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;
    @Basic
    @Column(name = "balance")
    private BigDecimal balance;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Basic
    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
