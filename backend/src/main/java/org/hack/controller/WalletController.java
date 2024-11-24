package org.hack.controller;

import lombok.RequiredArgsConstructor;
import org.hack.dto.UserDto;
import org.hack.dto.WalletDto;
import org.hack.entity.User;
import org.hack.entity.Wallet;
import org.hack.mapper.WalletMapper;
import org.hack.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    @GetMapping()
    public ResponseEntity<List<WalletDto>> getAllWallets() {
        List<WalletDto> wallets = walletService.findAll();
        return ResponseEntity.ok(wallets);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<WalletDto> getWalletByUserId(@PathVariable Long id) {
        return ResponseEntity.ok().body(walletService.findByUserId(id));
    }
    @GetMapping("/login/{login}")
    public ResponseEntity<WalletDto> getWalletByUserLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(walletService.findByUserLogin(login));
    }
}
