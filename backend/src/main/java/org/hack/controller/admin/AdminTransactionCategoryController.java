package org.hack.controller.admin;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.TransactionCategoryRequest;
import org.hack.service.TransactionCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/transactions/categories")
@RequiredArgsConstructor
public class AdminTransactionCategoryController {
    private final TransactionCategoryService transactionCategoryService;
    @PostMapping()
    public ResponseEntity<?> createNewTransactionCategory(
            @RequestBody TransactionCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionCategoryService.createNewTransactionCategory(request));
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteTransactionCategoryById(@PathVariable Integer categoryId) {
        transactionCategoryService.deleteTransactionCategoryById(categoryId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteTransactionCategoryByName(@RequestParam String name) {
        transactionCategoryService.deleteTransactionCategoryByName(name);
        return ResponseEntity.ok().build();
    }
}
