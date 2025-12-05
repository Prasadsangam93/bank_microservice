package com.bank.account.service;

import com.bank.account.client.CustomerClient;
import com.bank.account.dto.AccountResponse;
import com.bank.account.dto.CustomerResponse;
import com.bank.account.entity.Account;
import com.bank.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repo;
    private final CustomerClient customerClient;

    public AccountService(AccountRepository repo, CustomerClient customerClient) {
        this.repo = repo;
        this.customerClient = customerClient;
    }

    public Account create(Account account) {
        return repo.save(account);
    }

    public List<Account> getAccountsByCustomer(Long customerId) {
        return repo.findByCustomerId(customerId);
    }

    public AccountResponse getAccountWithCustomer(Long id) {
        Account acc = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        CustomerResponse customer = customerClient.getCustomer(acc.getCustomerId());

        return new AccountResponse(
                acc.getId(),
                acc.getAccountNumber(),
                acc.getAccountType(),
                acc.getBalance(),
                customer
        );
    }
}
