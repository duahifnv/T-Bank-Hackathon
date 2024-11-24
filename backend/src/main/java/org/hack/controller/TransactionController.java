package org.hack.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.hack.dto.TransactionDto;
import org.hack.entity.Transaction;
import org.hack.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping()
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok(transactionService.findAll());
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная выборка"),
            @ApiResponse(responseCode = "404", description = "Логина нет в системе"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{login}")
    public ResponseEntity<?> getAllTransactionsByUserLogin(@PathVariable String login) {
        Optional<List<TransactionDto>> transactionDtoList = transactionService.findAllByUserLogin(login);
        if (transactionDtoList.isPresent()) {
            return ResponseEntity.ok().body(transactionDtoList.get());
        }
        return ResponseEntity.notFound().build();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная выборка"),
            @ApiResponse(responseCode = "404", description = "Логина нет в системе"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{login}/{type}")
    public ResponseEntity<?> getAllTransactionsByUserIdAndTransactionType(@PathVariable String login,
                                                               @PathVariable String type) {
        Optional<List<TransactionDto>> transactionDtoList = transactionService.findAllByUserLoginAndTransactionType(login, type);
        if (transactionDtoList.isPresent()) {
            return ResponseEntity.ok().body(transactionDtoList.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.add(transactionDto));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное удаление"),
            @ApiResponse(responseCode = "404", description = "Логина нет в системе"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{login}")
    public ResponseEntity<?> deleteTransactionByLogin(@PathVariable String login) {
        Optional<TransactionDto> deletedTransactionDto = transactionService.deleteByLogin(login);
        if (deletedTransactionDto.isPresent()) {
            return ResponseEntity.ok().body(deletedTransactionDto);
        }
        return ResponseEntity.notFound().build();
    }
}
