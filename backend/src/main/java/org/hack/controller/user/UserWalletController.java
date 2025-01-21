package org.hack.controller.user;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.WalletRequest;
import org.hack.mapper.WalletMapper;
import org.hack.service.UserService;
import org.hack.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/me/wallets")
@RequiredArgsConstructor
public class UserWalletController {
    private final WalletService walletService;
    private final WalletMapper walletMapper;
    private final UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllWalletsByUsername(Principal principal) {
        return ResponseEntity.ok(walletMapper.toWalletResponse(
                walletService.findAllWalletsByUsername(principal.getName()))
        );
    }
    @PostMapping
    public ResponseEntity<?> createNewWallet(@RequestBody WalletRequest walletRequest,
                                             Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(walletMapper.toWalletResponse(
                        walletService.createNewWallet(
                                walletRequest, userService.findUserByUsername(principal.getName()))
                ));
    }
    @DeleteMapping("/{walletId}")
    public ResponseEntity<?> deleteWalletById(@PathVariable Long walletId,
                                              Principal principal) {
        walletService.deleteWalletById(walletId, principal);
        return ResponseEntity.ok().build();
    }
}
