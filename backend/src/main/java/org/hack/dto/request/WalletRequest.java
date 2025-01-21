package org.hack.dto.request;

import java.math.BigDecimal;

public record WalletRequest(String name, BigDecimal balance) {}
