package com.securitydemo.config;

import com.securitydemo.model.Customer;
import com.securitydemo.repository.CustomerRepository;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityDemoUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public SecurityDemoUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities;
        Optional<Customer> customer = customerRepository.findByEmail(username);
        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        } else {
            val customerEntity = customer.get();
            userName = customerEntity.getEmail();
            password = customerEntity.getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customerEntity.getRole()));
        }
        return new User(userName, password, authorities);
    }
}