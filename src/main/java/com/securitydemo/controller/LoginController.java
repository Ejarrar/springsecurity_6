package com.securitydemo.controller;

import com.securitydemo.model.Customer;
import com.securitydemo.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {

    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> customers = customerRepository.findByEmail(authentication.getName());
        return customers.stream().findFirst().orElse(null);
    }
}
