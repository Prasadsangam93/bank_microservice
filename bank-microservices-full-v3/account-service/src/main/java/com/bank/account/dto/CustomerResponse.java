package com.bank.account.dto;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String fullName;
    private String email;
    private String role;
}
