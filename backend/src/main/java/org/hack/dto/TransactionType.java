package org.hack.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TransactionType {
    INCOME(1),
    EXPENSE(2);
    private final int code;
}
