package org.hack.controller.guest;

import lombok.RequiredArgsConstructor;
import org.hack.service.TransactionCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info/transactions/categories")
@RequiredArgsConstructor
public class TransactionCategoryController {
    private final TransactionCategoryService transactionCategoryService;
    @GetMapping
    public ResponseEntity<?> getTransactionCategories(
            @RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(transactionCategoryService
                    .findTransactionCategoryByName(name));
        }
        return ResponseEntity.ok(transactionCategoryService
                .findAllTransactionCategories());
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getTransactionCategoryById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(transactionCategoryService
                .findTransactionCategoryById(categoryId));
    }
}
