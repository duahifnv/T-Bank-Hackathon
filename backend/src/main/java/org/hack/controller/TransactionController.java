package org.hack.controller;

import lombok.RequiredArgsConstructor;
import org.hack.dto.TransactionDto;
import org.hack.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping()
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok(transactionService.findAll());
    }
    @GetMapping("/{login}")
    public ResponseEntity<?> getAllTransactionsByUserLogin(@PathVariable String login) {
        return ResponseEntity.ok(transactionService.findAllByUserLogin(login));
    }
    @GetMapping("/{login}/{type}")
    public ResponseEntity<?> getAllTransactionsByUserIdAndType(@PathVariable String login,
                                                               @PathVariable String type) {
        return ResponseEntity.ok(transactionService.findAllByUserLoginAndTransactionType(login, type));
    }
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.add(transactionDto));
    }
}
