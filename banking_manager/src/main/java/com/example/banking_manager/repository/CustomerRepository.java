package com.example.banking_manager.repository;

import com.example.banking_manager.model.Customer;
import com.example.banking_manager.model.dto.DepositDTO;
import com.example.banking_manager.model.dto.RecipientDTO;
import com.example.banking_manager.model.dto.WithdrawDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Iterable<Customer> findAllByDeletedIsFalse();

    @Query("SELECT NEW com.example.banking_manager.model.dto.DepositDTO (c.id, c.fullName, c.balance) FROM Customer c WHERE c.id = ?1 ")
    Optional<DepositDTO> findByIdWithDepositDTO(Long id);


    @Query("SELECT NEW com.example.banking_manager.model.dto.WithdrawDTO (c.id, c.fullName, c.balance) FROM Customer c WHERE c.id = ?1 ")
    Optional<WithdrawDTO> findByIdWithWithdrawDTO(Long id);


    @Query("SELECT NEW com.example.banking_manager.model.dto.RecipientDTO (c.id, c.fullName) FROM Customer c WHERE c.id <> ?1 ")
    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSender(Long id);


    @Query("SELECT NEW com.example.banking_manager.model.dto.RecipientDTO (c.id, c.fullName) FROM Customer c WHERE c.id <> ?1 AND c.deleted = false ")
    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSenderAndDeletedIsFalse(Long id);

    //    @Modifying
    @Modifying(flushAutomatically = true)
    @Query("UPDATE Customer c SET c.balance = c.balance + :balance WHERE c.id = :id ")
    void updateBalance(@Param("balance") BigDecimal balance, @Param("id") Long id);


    @Modifying(flushAutomatically = true)
    @Query("UPDATE Customer c SET c.balance = c.balance + :transferAmount WHERE c.id = :id ")
    void incrementBalance(@Param("transferAmount") BigDecimal transferAmount, @Param("id") Long id);


    @Modifying(flushAutomatically = true)
    @Query("UPDATE Customer c SET c.balance = c.balance - :transactionAmount WHERE c.id = :id ")
    void reduceBalance(@Param("transactionAmount") BigDecimal transactionAmount, @Param("id") Long id);

    Boolean existsByEmail(String email);
}
