package org.hack.service;

import lombok.RequiredArgsConstructor;
import org.hack.dto.TransactionType;
import org.hack.dto.request.TransactionRequest;
import org.hack.entity.Transaction;
import org.hack.entity.Wallet;
import org.hack.mapper.TransactionMapper;
import org.hack.repository.TransactionCategoryRepository;
import org.hack.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final TransactionMapper transactionMapper;
    private final WalletService walletService;
    private final UserService userService;
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }
    public List<Transaction> findAllTransactionsByTypeId(Integer typeId) {
        if (!this.existsByTransactionTypeId(typeId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Не найден тип транзакции с id " + typeId);
        }
        return transactionRepository.findAllByTypeId(typeId);
    }
    public List<Transaction> findAllTransactionsByUsername(String username) {
        if (!userService.existsByUsername(username)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найден пользователь с именем " + username);
        }
        return transactionRepository.findAllByUsername(username);
    }
    public List<Transaction> findAllTransactionsByWalletId(Long walletId) {
        if (!walletService.existsById(walletId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Не найден кошелек с id " + walletId);
        }
        return transactionRepository.findAllByWalletId(walletId);
    }
    public List<Transaction> findAllTransactionsByTypeIdAndWalletId(Integer typeId, Long walletId) {
        if (!this.existsByTransactionTypeId(typeId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Не найден тип транзакции с id " + typeId);
        }
        if (!walletService.existsById(walletId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Не найден кошелек с id " + walletId);
        }
        return transactionRepository.findAllByTypeIdAndWalletId(typeId, walletId);
    }
    public List<Transaction> findAllTransactionsByTypeIdAndUsername(Integer typeId, String username) {
        if (!this.existsByTransactionTypeId(typeId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Не найден тип транзакции с id " + typeId);
        }
        if (!userService.existsByUsername(username)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Не найден пользователь с именем " + username);
        }
        return transactionRepository.findAllByTypeIdByUsername(typeId, username);
    }
    public Transaction findTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).
                orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найдена транзакция с id " + transactionId));
    }
    @Transactional
    public Transaction createNewTransaction(TransactionRequest transactionRequest, Principal principal) {
        List<Wallet> userWallets = walletService.findAllWalletsByUsername(principal.getName());
        userWallets.stream().
                map(Wallet::getWalletId)
                .filter(id -> id.equals(transactionRequest.walletId()))
                .findAny().orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.FORBIDDEN, "Нет доступа к кошельку с id " +
                                    transactionRequest.walletId()));
        return createNewTransaction(transactionRequest);
    }
    @Transactional
    public Transaction createNewTransaction(TransactionRequest transactionRequest) {
        try {
            validateTransactionRequest(transactionRequest);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        walletService.updateWalletBalance(transactionRequest.walletId(),
                transactionRequest.amount());
        return transactionRepository.save(
                transactionMapper.toTransaction(transactionRequest)
        );
    }
    @Transactional
    public void deleteTransactionById(Long transactionId) {
        if (!this.existsByTransactionId(transactionId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найдена транзакция с id " + transactionId);
        }
        transactionRepository.deleteById(transactionId);
    }
    public boolean existsByTransactionId(Long transactionId) {
        return transactionRepository.existsById(transactionId);
    }
    private void validateTransactionRequest(TransactionRequest transactionRequest)
            throws Exception {
        if (!this.existsByTransactionTypeId(transactionRequest.typeId())) {
            throw new Exception("Не существует типа транзакции с кодом " +
                    transactionRequest.typeId());
        }
        if (!this.existsByTransactionCategoryId(transactionRequest.categoryId())) {
            throw new Exception("Не существует категории транзакции с кодом " +
                    transactionRequest.categoryId());
        }
        if (!walletService.existsById(transactionRequest.walletId())) {
            throw new Exception("Не существует кошелька с кодом " +
                    transactionRequest.walletId());
        }
    }
    private boolean existsByTransactionTypeId(Integer typeId) {
        return Arrays.stream(TransactionType.values())
                .map(TransactionType::getCode)
                .anyMatch(x -> x.equals(typeId));
    }
    private boolean existsByTransactionCategoryId(Integer categoryId) {
        return transactionCategoryRepository.existsById(categoryId);
    }
}
