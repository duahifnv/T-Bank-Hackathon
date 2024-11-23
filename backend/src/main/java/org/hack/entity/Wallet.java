package org.hack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Wallet {
    @Id
    @PrimaryKeyJoinColumn(name = "user_id")
    private Long userId;
    @Column(name = "total", nullable = false)
    private double total;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
