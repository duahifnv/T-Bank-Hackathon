package org.hack.mapper;

import org.hack.dto.WalletDto;
import org.hack.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.Serializable;
import java.util.List;

// TODO: Адекватный маппинг
@Mapper(componentModel = "spring")
public interface WalletMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "total", target = "total")
    Wallet dtoToModel(WalletDto walletDto);
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "total", target = "total")
    WalletDto modelToDto(Wallet wallet);
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "total", target = "total")
    List<WalletDto> toListDto(List<Wallet> wallets);
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "total", target = "total")
    List<Wallet> toListModel(List<WalletDto> walletsDto);
}
