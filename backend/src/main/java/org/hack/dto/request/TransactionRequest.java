package org.hack.dto.request;

import java.math.BigDecimal;

public record TransactionRequest(Long walletId,
                                 Integer typeId,
                                 BigDecimal amount,
                                 Integer categoryId) {}
