package org.hack.service;

import org.hack.dto.UserDto;
import org.hack.dto.WalletDto;
import org.hack.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    List<WalletDto> findAll();
    WalletDto findById(Long userId);
    WalletDto findByUserId(Long userId);
    WalletDto findByUserLogin(String login);
    WalletDto save(WalletDto walletDto);
    void deleteById(Long userId);
    void createWalletForUser(UserDto userDto);
    void changeTotal(Long userId, BigDecimal amount);
}
