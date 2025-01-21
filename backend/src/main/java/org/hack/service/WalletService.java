package org.hack.service;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.WalletRequest;
import org.hack.entity.User;
import org.hack.entity.Wallet;
import org.hack.mapper.WalletMapper;
import org.hack.repository.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final UserService userService;
    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }
    public List<Wallet> findAllWalletsByUserId(Long userId) {
        if (!userService.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найден пользователь с id " + userId);
        }
        return walletRepository.findAllByUserId(userId);
    }
    public List<Wallet> findAllWalletsByUsername(String username) {
        if (!userService.existsByUsername(username)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найден пользователь с именем " + username);
        }
        return walletRepository.findAllByUsername(username);
    }
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найден кошелек с id " + walletId));
    }
    @Transactional
    public Wallet createNewWallet(WalletRequest walletRequest, User user) {
        if (walletRequest.balance().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Стартовый баланс кошелька не может быть меньше нуля");
        }
        return this.saveWallet(
                walletMapper.toWallet(walletRequest, user)
        );
    }
    @Transactional
    public void deleteWalletById(Long walletId) {
        if (!this.existsById(walletId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найден кошелек с id " + walletId);
        }
        walletRepository.deleteById(walletId);
    }
    public BigDecimal getWalletBalance(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Не найден кошелек с id " + walletId)).getBalance();
    }
    @Transactional
    public void updateWalletBalance(Long walletId, BigDecimal change) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Не найден кошелек с id " + walletId));
        if (wallet.getBalance()
                .compareTo(BigDecimal.ZERO.subtract(change)) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Транзакция не выполнима: баланс кошелька меньше (или станет меньше) нуля");
        }
        wallet.setBalance(wallet.getBalance().add(change));
        this.saveWallet(wallet);
    }
    public boolean existsById(Long walletId) {
        return walletRepository.existsById(walletId);
    }
    private Wallet saveWallet(Wallet wallet) {
        wallet.setLastUpdateDate(LocalDate.now());
        return walletRepository.save(wallet);
    }
}
