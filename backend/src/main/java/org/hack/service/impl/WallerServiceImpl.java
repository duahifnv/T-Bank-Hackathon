package org.hack.service.impl;

import lombok.RequiredArgsConstructor;
import org.hack.dto.WalletDto;
import org.hack.entity.User;
import org.hack.entity.Wallet;
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
    public WalletDto findById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent()) {
            Wallet walletDto = wallet.get();
            return walletMapper.modelToDto(walletDto);
        }
        throw new RuntimeException("Отсутствует кошелек с id кошелька " + id);
    }
    @Override
    public WalletDto findByUserId(User id) {
        Optional<Wallet> wallet = walletRepository.findByUserId(id);
        if (wallet.isPresent()) {
            Wallet walletFromRepo = wallet.get();
            return walletMapper.modelToDto(walletFromRepo);
        }
        throw new RuntimeException("Отсутствует кошелек с id пользователя " + id.getId());
    }
    @Override
    public WalletDto save(WalletDto walletDto) {
        Wallet wallet = walletRepository.save(walletMapper.dtoToModel(walletDto));
        return walletMapper.modelToDto(wallet);
    }
    @Override
    public void deleteById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent()) {
            Wallet walletFromRepo = wallet.get();
            walletRepository.deleteById(id);
        }
        throw new RuntimeException("Отсутствует кошелек с id кошелька " + id);
    }
}
