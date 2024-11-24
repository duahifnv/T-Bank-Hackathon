package org.hack.service;

import org.hack.dto.TransactionDto;
import org.hack.dto.UserDto;
import org.hack.dto.WalletDto;
import org.hack.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<TransactionDto> findAll();
    Optional<List<TransactionDto>> findAllByUserLogin(String login);
    Optional<List<TransactionDto>> findAllByUserLoginAndTransactionType(String login, String type);
    TransactionDto add(TransactionDto transactionDto);
    Optional<TransactionDto> deleteByLogin(String login);
}
