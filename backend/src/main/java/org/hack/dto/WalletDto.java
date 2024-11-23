package org.hack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hack.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {
    private Long id;
    private User userId;
    private double total;
}
