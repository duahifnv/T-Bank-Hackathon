package org.hack.repository;

import org.hack.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("select w from Wallet w where w.user.userId = ?1")
    List<Wallet> findAllByUserId(Long userId);
    @Query("select w from Wallet w where w.user.username = ?1")
    List<Wallet> findAllByUsername(String username);
}
