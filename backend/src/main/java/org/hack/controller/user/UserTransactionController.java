package org.hack.controller.user;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.TransactionRequest;
import org.hack.mapper.TransactionMapper;
import org.hack.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/me/transactions")
@RequiredArgsConstructor
public class UserTransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    @GetMapping()
    public ResponseEntity<?> getAllUserTransactions(Principal principal) {
        return ResponseEntity.ok(transactionMapper.toTransactionResponse(
                transactionService.findAllTransactionsByUsername(principal.getName())));
    }
    @GetMapping("/types/{typeId}")
    public ResponseEntity<?> getAllUserTransactionsByTypeId(@PathVariable Integer typeId,
                                                            Principal principal) {
        return ResponseEntity.ok(transactionMapper.toTransactionResponse(
                transactionService.findAllTransactionsByTypeIdAndUsername(typeId, principal.getName())));
    }
    @PostMapping()
    public ResponseEntity<?> createNewTransaction(
            @RequestBody TransactionRequest transactionRequest,
            Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionMapper.toTransactionResponse(
                        transactionService.createNewTransaction(transactionRequest, principal)));
    }
}
