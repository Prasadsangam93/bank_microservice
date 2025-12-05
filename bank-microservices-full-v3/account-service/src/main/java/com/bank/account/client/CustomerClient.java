package com.bank.account.client;

import com.bank.account.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

    @GetMapping("/customer/{id}")
    CustomerResponse getCustomer(@PathVariable("id") Long id);
}
