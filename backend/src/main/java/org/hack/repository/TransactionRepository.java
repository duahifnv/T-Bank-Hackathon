package org.hack.repository;

import org.hack.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByTypeId(Integer typeId);
    @Query("select t from Transaction t where t.wallet.walletId = ?1")
    List<Transaction> findAllByWalletId(Long walletId);
    @Query("select t from Transaction t where t.typeId = ?1 and t.wallet.walletId = ?2")
    List<Transaction> findAllByTypeIdAndWalletId(Integer typeId, Long walletId);
    @Query("select t from Transaction t inner join Wallet w " +
            "on t.wallet.walletId = w.walletId where w.user.username = ?1")
    List<Transaction> findAllByUsername(String username);
    @Query("select t from Transaction t inner join Wallet w " +
            "on t.wallet.walletId = w.walletId where t.typeId = ?1 " +
            "and w.user.username = ?2")
    List<Transaction> findAllByTypeIdByUsername(Integer typeId, String username);
}
