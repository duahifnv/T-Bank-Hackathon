package org.hack.dto.request;

import java.math.BigDecimal;

public record WalletBalanceUpdateRequest(BigDecimal change) {
}
