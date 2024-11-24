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
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllTransactionsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findAllByUserId(id));
    }
    @GetMapping("/{id}/{type}")
    public ResponseEntity<?> getAllTransactionsByUserIdAndType(@PathVariable Long id,
                                                               @PathVariable String type) {
        return ResponseEntity.ok(transactionService.findAllByUserIdAndTransactionType(id, type));
    }
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.add(transactionDto));
    }
}
