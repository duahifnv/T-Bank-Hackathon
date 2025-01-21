package org.hack.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(Long transactionId,
                                  LocalDate date,
                                  Integer typeId,
                                  Long walletId,
                                  BigDecimal amount,
                                  Integer categoryId) {
}
