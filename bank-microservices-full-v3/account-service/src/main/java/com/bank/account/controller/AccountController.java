package com.bank.account.controller;

import com.bank.account.dto.AccountResponse;
import com.bank.account.entity.Account;
import com.bank.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.ok(service.create(account));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getAccountsByCustomer(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountWithCustomer(id));
    }
}
