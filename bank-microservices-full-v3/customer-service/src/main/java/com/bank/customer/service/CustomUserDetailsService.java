package com.bank.customer.service;

import com.bank.customer.entity.Customer;
import com.bank.customer.repository.CustomerRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    public CustomUserDetailsService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer c = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return User.builder()
                .username(c.getEmail())
                .password(c.getPassword())
                .roles(c.getRole().replace("ROLE_", ""))
                .build();
    }
}
