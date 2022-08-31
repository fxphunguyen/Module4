package com.codegym.customer_manager.service;

import com.codegym.customer_manager.model.Customer;

public interface ICustomerService {
    boolean insertWithStoredProcedure(Customer customer);
}
