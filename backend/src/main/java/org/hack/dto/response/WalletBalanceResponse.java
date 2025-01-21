package org.hack.dto.response;

import java.math.BigDecimal;

public record WalletBalanceResponse(Long walletId, BigDecimal balance) {}