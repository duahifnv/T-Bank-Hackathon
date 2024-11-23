package org.hack.mapper;

import org.hack.dto.WalletDto;
import org.hack.entity.Wallet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    Wallet dtoToModel(WalletDto walletDto);
    WalletDto modelToDto(Wallet wallet);
    List<WalletDto> toListDto(List<Wallet> wallets);
}
