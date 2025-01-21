package org.hack.service;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.TransactionCategoryRequest;
import org.hack.entity.TransactionCategory;
import org.hack.mapper.TransactionMapper;
import org.hack.repository.TransactionCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCategoryService {
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final TransactionMapper transactionMapper;
    public List<TransactionCategory> findAllTransactionCategories() {
        return transactionCategoryRepository.findAll();
    }
    public TransactionCategory findTransactionCategoryById(Integer categoryId) {
        return transactionCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найдена категория транзакции с id " + categoryId));
    }
    public TransactionCategory findTransactionCategoryByName(String name) {
        return transactionCategoryRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найдена категория транзакции с именем " + name));
    }
    @Transactional
    public TransactionCategory createNewTransactionCategory(
            TransactionCategoryRequest transactionCategoryRequest) {
        if (existsByTransactionCategoryName(transactionCategoryRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Уже существует категория транзакции с именем " +
                    transactionCategoryRequest.name());
        }
        return transactionCategoryRepository.save(
                transactionMapper.toTransactionCategory(transactionCategoryRequest)
        );
    }
    @Transactional
    public void deleteTransactionCategoryById(Integer categoryId) {
        if (!this.existsByTransactionCategoryId(categoryId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найдена категория транзакции с id " + categoryId);
        }
        transactionCategoryRepository.deleteById(categoryId);
    }
    @Transactional
    public void deleteTransactionCategoryByName(String name) {
        if (!this.existsByTransactionCategoryName(name)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найдена категория транзакции с именем " + name);
        }
        transactionCategoryRepository.deleteByName(name);
    }
    private boolean existsByTransactionCategoryId(Integer categoryId) {
        return transactionCategoryRepository.existsById(categoryId);
    }
    private boolean existsByTransactionCategoryName(String name) {
        return transactionCategoryRepository.existsByName(name);
    }
}
