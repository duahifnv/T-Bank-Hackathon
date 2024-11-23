package org.hack.service;

import org.hack.dto.UserDto;
import org.hack.dto.WalletDto;
import org.hack.entity.User;

import java.util.List;

public interface WalletService {
    List<WalletDto> findAll();
    WalletDto findById(Long userId);
    WalletDto findByUserId(Long userId);
    WalletDto save(WalletDto walletDto);
    void deleteById(Long userId);
    void createWalletForUser(UserDto userDto);
    void increaseByAmount(Long userId, double amount);
    void decreaseByAmount(Long userId, double amount);
}
