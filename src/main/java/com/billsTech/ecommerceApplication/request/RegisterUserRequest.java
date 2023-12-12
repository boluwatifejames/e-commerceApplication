package com.billsTech.ecommerceApplication.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
