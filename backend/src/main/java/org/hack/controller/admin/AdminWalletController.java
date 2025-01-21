package org.hack.controller.admin;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.WalletBalanceUpdateRequest;
import org.hack.dto.request.WalletRequest;
import org.hack.dto.response.WalletBalanceResponse;
import org.hack.mapper.WalletMapper;
import org.hack.service.UserService;
import org.hack.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/wallets")
@RequiredArgsConstructor
public class AdminWalletController {
    private final WalletService walletService;
    private final WalletMapper walletMapper;
    private final UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllWallets() {
        return ResponseEntity.ok(walletMapper.toWalletResponse(
                        walletService.findAllWallets()));
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getAllWalletsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(walletMapper.toWalletResponse(
                        walletService.findAllWalletsByUserId(userId)));
    }
    @GetMapping("/{walletId}")
    public ResponseEntity<?> getWalletById(@PathVariable Long walletId) {
        return ResponseEntity.ok(walletMapper.toWalletResponse(
                        walletService.findWalletById(walletId)));
    }
    @PostMapping("/users/{userId}")
    public ResponseEntity<?> createNewWallet(@PathVariable Long userId,
                                             @RequestBody WalletRequest walletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(walletMapper.toWalletResponse(
                        walletService.createNewWallet(walletRequest, userService.findUserById(userId)))
                );
    }
    @GetMapping("/balance/{walletId}")
    public ResponseEntity<?> getWalletBalanceById(@PathVariable Long walletId) {
        return ResponseEntity.ok(
                new WalletBalanceResponse(walletId, walletService.getWalletBalance(walletId))
        );
    }
    @PostMapping("/balance/{walletId}")
    public ResponseEntity<?> updateWalletBalanceById(@PathVariable Long walletId,
                                                     @RequestBody WalletBalanceUpdateRequest walletBalanceUpdateRequest) {
        walletService.updateWalletBalance(walletId, walletBalanceUpdateRequest.change());
        return ResponseEntity.ok(
                new WalletBalanceResponse(walletId, walletService.getWalletBalance(walletId))
        );
    }
    @DeleteMapping("/{walletId}")
    public ResponseEntity<?> deleteWalletById(@PathVariable Long walletId) {
        walletService.deleteWalletById(walletId);
        return ResponseEntity.ok().build();
    }
}
