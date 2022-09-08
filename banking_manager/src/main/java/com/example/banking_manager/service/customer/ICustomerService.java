package com.example.banking_manager.service.customer;

import com.example.banking_manager.model.Customer;
import com.example.banking_manager.model.dto.DepositDTO;
import com.example.banking_manager.model.dto.RecipientDTO;
import com.example.banking_manager.model.dto.TransferDTO;
import com.example.banking_manager.model.dto.WithdrawDTO;
import com.example.banking_manager.service.IGeneralService;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Boolean existsByEmail(String email);

    Iterable<Customer> findAllByDeletedIsFalse();

    Optional<DepositDTO> findByIdWithDepositDTO(Long id);

    Optional<WithdrawDTO> findByIdWithWithdrawDTO(Long id);

    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSender(Long id);

    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSenderAndDeletedIsFalse(Long id);

    void doDeposit(Long customerId, BigDecimal transactionAmount, DepositDTO depositDTO);

    void doWithdraw(Long customerId, BigDecimal transactionAmount, WithdrawDTO withdrawDTO);

    void doTransfer(TransferDTO transferDTO);

    void incrementBalance(BigDecimal balance, Long id);

    void reduceBalance(BigDecimal balance, Long id);
}
