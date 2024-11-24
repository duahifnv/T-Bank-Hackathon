package org.hack.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная выборка всех кошельков")
    })
    @GetMapping()
    public ResponseEntity<List<WalletDto>> getAllWallets() {
        List<WalletDto> wallets = walletService.findAll();
        return ResponseEntity.ok(wallets);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешная выборка кошелька пользователя по его id")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<WalletDto> getWalletByUserId(@PathVariable Long id) {
        return ResponseEntity.ok().body(walletService.findByUserId(id));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешная выборка кошелька пользователя по его логину")
    })
    @GetMapping("/login/{login}")
    public ResponseEntity<WalletDto> getWalletByUserLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(walletService.findByUserLogin(login));
    }
}
