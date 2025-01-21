package org.hack.controller.admin;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.TransactionRequest;
import org.hack.mapper.TransactionMapper;
import org.hack.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/transactions")
@RequiredArgsConstructor
public class AdminTransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    @GetMapping()
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok(
                transactionMapper.toTransactionResponse(transactionService.findAllTransactions())
        );
    }
    @GetMapping("/types/{typeId}")
    public ResponseEntity<?> getAllTransactionsByTypeId(@PathVariable Integer typeId) {
        return ResponseEntity.ok(
                transactionMapper.toTransactionResponse(
                        transactionService.findAllTransactionsByTypeId(typeId)
                )
        );
    }
    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<?> getAllTransactionsByWalletId(@PathVariable Long walletId) {
        return ResponseEntity.ok(
                transactionMapper.toTransactionResponse(
                        transactionService.findAllTransactionsByWalletId(walletId)
                )
        );
    }
    @GetMapping("/types/{typeId}/wallets/{walletId}")
    public ResponseEntity<?> getAllTransactionsByTypeIdAndWalletId(
            @PathVariable Integer typeId,
            @PathVariable Long walletId) {
        return ResponseEntity.ok(
                transactionMapper.toTransactionResponse(
                        transactionService.findAllTransactionsByTypeIdAndWalletId(typeId, walletId)
                )
        );
    }
    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long transactionId) {
        return ResponseEntity.ok(
                transactionMapper.toTransactionResponse(
                        transactionService.findTransactionById(transactionId)
                )
        );
    }
    @PostMapping()
    public ResponseEntity<?> createNewTransaction(
            @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionMapper.toTransactionResponse(
                        transactionService.createNewTransaction(transactionRequest)
                ));
    }
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable Long transactionId) {
        transactionService.deleteTransactionById(transactionId);
        return ResponseEntity.ok().build();
    }
}
