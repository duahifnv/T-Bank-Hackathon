package org.hack.mapper;

import org.hack.dto.TransactionDto;
import org.hack.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {
    Transaction dtoToModel(TransactionDto transactionDto);
    @Mapping(source = "user.id", target = "userId")
    TransactionDto modelToDto(Transaction transaction);
    List<TransactionDto> toListDto(List<Transaction> transaction);
}
