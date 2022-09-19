package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ICustomerService extends IGeneralService<Customer> {

    List<Customer> findAllByIdNot(Long senderId);

    Customer deposit(Deposit deposit);

    Customer withdraw(Withdraw withdraw);

    public Map<String, CustomerDTO> transfer(Transfer transfer);

    void incrementMoney(BigDecimal transactionAmount, Long customerId);
    void reduceMoney(BigDecimal transactionAmount, Long customerId);
}