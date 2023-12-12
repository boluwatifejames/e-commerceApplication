package com.billsTech.ecommerceApplication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticateUserRequest {
    private String email;
    private String password;
}
