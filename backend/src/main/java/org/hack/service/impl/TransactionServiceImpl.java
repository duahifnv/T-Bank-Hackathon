package org.hack.service.impl;

import lombok.RequiredArgsConstructor;
import org.hack.dto.TransactionDto;
import org.hack.dto.UserDto;
import org.hack.entity.Transaction;
import org.hack.entity.User;
import org.hack.mapper.TransactionMapper;
import org.hack.mapper.UserMapper;
import org.hack.repository.TransactionRepository;
import org.hack.service.TransactionService;
import org.hack.service.UserService;
import org.hack.service.WalletService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WalletService walletService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final static List<String> transactionTypes =
            List.of("income", "expense");
    private final static List<String> transactionCategories =
            List.of("entertainment", "food", "transport", "clothes", "health");
    @Override
    public List<TransactionDto> findAll() {
        return transactionMapper.toListDto(transactionRepository.findAll());
    }
    @Override
    public List<TransactionDto> findAllByUserLogin(String login) {
        List<Transaction> transactions =
                transactionRepository.findAllByUserLogin(login)
                        .orElseThrow(RuntimeException::new);
        return transactionMapper.toListDto(transactions);
    }
    @Override
    public List<TransactionDto> findAllByUserLoginAndTransactionType(String login, String type) {
        if (!transactionTypes.contains(type)) {
            throw new RuntimeException("Не существующий тип");
        }
        List<Transaction> transactions =
                transactionRepository.findAllByUserLoginAndTransactionType(login, type)
                        .orElseThrow(RuntimeException::new);
        return transactionMapper.toListDto(transactions);
    }
    @Override
    public TransactionDto add(TransactionDto transactionDto) {
        walletService.changeTotal(transactionDto.getUserId(), transactionDto.getAmount());
        Transaction transaction = transactionMapper.dtoToModel(transactionDto);
        UserDto transactionUserDto = userService.findById(transactionDto.getUserId());
        transaction.setUser(userMapper.dtoToModel(transactionUserDto));
        transactionRepository.save(transaction);
        return transactionMapper.modelToDto(transaction);
    }
    @Override
    public void deleteByLogin(String login) {
        Transaction transactionOptional =
                transactionRepository.findByUserLogin(login)
                        .orElseThrow(RuntimeException::new);
        transactionRepository.delete(transactionOptional);
    }
}
