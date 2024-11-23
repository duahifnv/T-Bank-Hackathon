package org.hack.service.impl;

import lombok.RequiredArgsConstructor;
import org.hack.dto.UserDto;
import org.hack.dto.WalletDto;
import org.hack.entity.Wallet;
import org.hack.mapper.UserMapper;
import org.hack.mapper.WalletMapper;
import org.hack.repository.WalletRepository;
import org.hack.service.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WallerServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    @Override
    public List<WalletDto> findAll() {
        List<Wallet> wallets = walletRepository.findAll();
        return walletMapper.toListDto(wallets);
    }
    @Override
    public WalletDto findById(Long userId) {
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        if (wallet.isPresent()) {
            Wallet walletDto = wallet.get();
            return walletMapper.modelToDto(walletDto);
        }
        throw new RuntimeException("Отсутствует кошелек с id кошелька " + userId);
    }
    @Override
    public void createWalletForUser(UserDto userDto) {
        Long userId = userDto.getId();
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        if (wallet.isEmpty()) {
            WalletDto walletDto = new WalletDto(userId, 0);
            walletRepository.save(walletMapper.dtoToModel(walletDto));
        }
        throw new RuntimeException("У пользователя " + userId + " уже есть кошелек");
    }
    @Override
    public WalletDto findByUserId(Long userId) {
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        if (wallet.isPresent()) {
            Wallet walletFromRepo = wallet.get();
            return walletMapper.modelToDto(walletFromRepo);
        }
        throw new RuntimeException("Отсутствует кошелек с id пользователя " + userId);
    }
    @Override
    public WalletDto save(WalletDto walletDto) {
        Wallet wallet = walletRepository.save(walletMapper.dtoToModel(walletDto));
        return walletMapper.modelToDto(wallet);
    }
    @Override
    public void deleteById(Long userId) {
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        if (wallet.isPresent()) {
            walletRepository.deleteByUserId(userId);
        }
        throw new RuntimeException("Отсутствует кошелек с id кошелька " + userId);
    }
    @Override
    public void increaseByAmount(Long userId, double amount) {

    }
    @Override
    public void decreaseByAmount(Long userId, double amount) {

    }
}
