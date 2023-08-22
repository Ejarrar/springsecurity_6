package com.securitydemo.controller;

import com.securitydemo.model.AccountTransaction;
import com.securitydemo.model.Customer;
import com.securitydemo.repository.AccountTransactionsRepository;
import com.securitydemo.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BalanceController {

    private final CustomerRepository customerRepository;

    private final AccountTransactionsRepository accountTransactionsRepository;

    public BalanceController(CustomerRepository customerRepository, AccountTransactionsRepository accountTransactionsRepository) {
        this.customerRepository = customerRepository;
        this.accountTransactionsRepository = accountTransactionsRepository;
    }

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> customers = customerRepository.findByEmail(email);
        return customers.map(customer -> accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(customer.getId())).orElse(null);
    }
}
