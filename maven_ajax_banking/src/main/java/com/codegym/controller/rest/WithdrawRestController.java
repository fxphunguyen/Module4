package com.codegym.controller.rest;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Withdraw;
import com.codegym.model.dto.CustomerDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/withdraws")
public class WithdrawRestController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IWithdrawService withdrawService;

    @PostMapping("/create")
    public ResponseEntity<?> withdraw(@RequestBody Withdraw withdraw) {

        Long customerId = withdraw.getCustomer().getId();

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>("Customer ID invalid", HttpStatus.BAD_REQUEST);
        }

        try {
            Customer customer = customerService.withdraw(withdraw);

            CustomerDTO customerDTO = customer.toCustomerDTO();

            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Please contact to administrator", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
