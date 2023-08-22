package com.securitydemo.controller;

import com.securitydemo.model.Customer;
import com.securitydemo.model.Loan;
import com.securitydemo.repository.CustomerRepository;
import com.securitydemo.repository.LoanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LoansController {

    private final LoanRepository loanRepository;

    private final CustomerRepository customerRepository;

    public LoansController(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/loans")
    public List<Loan> getLoanDetails(@RequestParam String email) {
        Optional<Customer> customers = customerRepository.findByEmail(email);
        return customers.map(customer -> loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId())).orElse(null);
    }
}
