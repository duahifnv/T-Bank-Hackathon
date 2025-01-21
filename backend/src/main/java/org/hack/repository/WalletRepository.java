package org.hack.repository;

import lombok.NonNull;
import org.hack.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("select w from Wallet w where w.user.userId = ?1")
    List<Wallet> findAllByUserId(Long userId);
    @Query("select w from Wallet w where w.user.username = ?1")
    List<Wallet> findAllByUsername(String username);
}
