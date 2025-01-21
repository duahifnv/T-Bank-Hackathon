package org.hack.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WalletResponse(Long walletId,
                             Long userId,
                             BigDecimal balance,
                             String name,
                             LocalDate issueDate,
                             LocalDate lastUpdateDate) {
}
