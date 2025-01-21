package org.hack.mapper;

import org.hack.dto.request.TransactionRequest;
import org.hack.dto.response.TransactionResponse;
import org.hack.entity.Transaction;
import org.hack.entity.Wallet;
import org.hack.service.WalletService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public abstract class TransactionMapper {
    @Autowired
    protected WalletService walletService;
    @Mapping(target = "date", expression = "java(LocalDate.now())")
    @Mapping(source = "walletId", target = "wallet", qualifiedByName = "walletIdToWallet")
    public abstract Transaction toTransaction(TransactionRequest transactionRequest);
    @Mapping(source = "wallet", target = "walletId", qualifiedByName = "walletToWalletId")
    public abstract TransactionResponse toTransactionResponse(Transaction transaction);
    public abstract List<TransactionResponse> toTransactionResponse(List<Transaction> transactions);
    @Named("walletIdToWallet")
    protected Wallet walletIdToWallet(Long walletId) {
        return walletService.findWalletById(walletId);
    }
    @Named("walletToWalletId")
    protected Long walletToWalletId(Wallet wallet) {
        return wallet.getWalletId();
    }
}
