package org.hack.mapper;

import org.hack.dto.request.WalletRequest;
import org.hack.dto.response.WalletResponse;
import org.hack.entity.User;
import org.hack.entity.Wallet;
import org.hack.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public abstract class WalletMapper {
    @Autowired
    protected UserService userService;
    @Mapping(target = "issueDate", expression = "java(LocalDate.now())")
    public abstract Wallet toWallet(WalletRequest walletRequest, User user);
    @Mapping(source = "user", target = "userId", qualifiedByName = "userToUserId")
    public abstract WalletResponse toWalletResponse(Wallet wallet);
    public abstract List<WalletResponse> toWalletResponse(List<Wallet> wallets);
    @Named("userToUserId")
    protected Long userToUserId(User user) {
        return user.getUserId();
    }
}
