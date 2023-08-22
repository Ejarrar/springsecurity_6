package com.securitydemo.controller;

import com.securitydemo.model.Card;
import com.securitydemo.model.Customer;
import com.securitydemo.repository.CardsRepository;
import com.securitydemo.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CardsController {

    private final CardsRepository cardsRepository;

    private final CustomerRepository customerRepository;

    public CardsController(CardsRepository cardsRepository, CustomerRepository customerRepository) {
        this.cardsRepository = cardsRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/myCards")
    public List<Card> getCardDetails(@RequestParam String email) {
        Optional<Customer> customers = customerRepository.findByEmail(email);
        return customers.map(customer -> cardsRepository.findByCustomerId(customer.getId())).orElse(null);
    }
}
