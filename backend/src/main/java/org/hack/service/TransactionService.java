package org.hack.service;

import org.hack.dto.TransactionDto;
import org.hack.dto.UserDto;
import org.hack.dto.WalletDto;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    List<TransactionDto> findAll();
    List<TransactionDto> findAllByUserId(Long userId);
    List<TransactionDto> findAllByUserIdAndTransactionType(Long userId, String type);
    TransactionDto add(TransactionDto transactionDto);
    void delete(Long userId);
}
