package org.hack.repository;

import org.hack.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findAllByUserLoginAndTransactionType(String login, String transactionType);
    Optional<List<Transaction>> findAllByUserLogin(String login);
    Optional<Transaction> findByUserLogin(String userLogin);
}
