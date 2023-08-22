package com.securitydemo.controller;

import com.securitydemo.model.Account;
import com.securitydemo.model.Customer;
import com.securitydemo.repository.AccountsRepository;
import com.securitydemo.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private final AccountsRepository accountsRepository;

    private final CustomerRepository customerRepository;

    public AccountController(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/myAccount")
    public Account getAccountDetails(@RequestParam String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.map(c -> accountsRepository.findByCustomerId(c.getId())).orElse(null);
    }
}
