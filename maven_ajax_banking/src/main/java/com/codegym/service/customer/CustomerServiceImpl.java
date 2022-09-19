package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.repository.CustomerRepository;
import com.codegym.repository.DepositRepository;
import com.codegym.repository.TransferRepository;
import com.codegym.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAllByIdNot(Long senderId) {
        return customerRepository.findAllByIdNot(senderId);
    }

    @Override
    public Customer deposit(Deposit deposit) {

        Long customerId = deposit.getCustomer().getId();

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();

        customerRepository.incrementMoney(deposit.getTransactionAmount(), customerId);

        depositRepository.save(deposit);

        BigDecimal newBalance = customer.getBalance().add(deposit.getTransactionAmount());

        customer.setBalance(newBalance);

        return customer;
    }

    @Override
    public Customer withdraw(Withdraw withdraw) {
        Long customerId = withdraw.getCustomer().getId();

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();

        customerRepository.incrementMoney(withdraw.getTransactionAmount(), customerId);

        withdrawRepository.save(withdraw);

        BigDecimal newBalance = customer.getBalance().add(withdraw.getTransactionAmount());

        customer.setBalance(newBalance);

        return customer;
    }

    @Override
    public Map<String, CustomerDTO> transfer(Transfer transfer) {
        BigDecimal balanceSender = transfer.getSender().getBalance();
        BigDecimal transactionAmount = transfer.getTransactionAmount();
        BigDecimal newBalanceSender = balanceSender.subtract(transactionAmount);
        transfer.getSender().setBalance(newBalanceSender);

        Customer sender = transfer.getSender();
        sender.setBalance(newBalanceSender);

        BigDecimal balanceRecipient = transfer.getRecipient().getBalance();
        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal newBalanceRecipient = balanceRecipient.add(transferAmount);
        transfer.getRecipient().setBalance(newBalanceRecipient);

        Customer recipient = transfer.getRecipient();
        recipient.setBalance(newBalanceRecipient);

        customerRepository.incrementMoney(transfer.getTransferAmount(), transfer.getRecipient().getId());

        customerRepository.reduceMoney(transfer.getTransactionAmount(), transfer.getSender().getId());

        transferRepository.save(transfer);

        Map<String, CustomerDTO> results = new HashMap<>();
        results.put("sender", sender.toCustomerDTO());
        results.put("recipient", recipient.toCustomerDTO());

        return results;
    }

    @Override
    public void incrementMoney(BigDecimal transactionAmount, Long customerId) {
        customerRepository.incrementMoney(transactionAmount, customerId);
    }

    @Override
    public void reduceMoney(BigDecimal transactionAmount, Long customerId) {
        customerRepository.reduceMoney(transactionAmount, customerId);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }
}
