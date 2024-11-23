package org.hack.service;

import org.hack.dto.WalletDto;
import org.hack.entity.User;

import java.util.List;

public interface WalletService {
    List<WalletDto> findAll();
    WalletDto findById(Long id);
    WalletDto findByUserId(User id);
    WalletDto save(WalletDto walletDto);
    void deleteById(Long id);
}
