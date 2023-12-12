package com.billsTech.ecommerceApplication.service;

import com.billsTech.ecommerceApplication.auth.Roles;
import com.billsTech.ecommerceApplication.auth.config.JwtService;
import com.billsTech.ecommerceApplication.entity.Users;
import com.billsTech.ecommerceApplication.repositories.UserRepo;
import com.billsTech.ecommerceApplication.repositories.UserRepo2;
import com.billsTech.ecommerceApplication.request.*;
import com.billsTech.ecommerceApplication.response.AuthenticateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginAndSignUp_Service {

    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final UserRepo2 userRepo2;
    private final AuthenticationManager authenticationManager;
    public AuthenticateUserResponse registerUser(RegisterUserRequest request) {
        var user = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Roles.USER)
                .build();
        user = userRepo.save(user);
        var user2 = userRepo2.save(user);
        return AuthenticateUserResponse.builder()
                .Status("Registered Successfully")
                .build();
    }


    public AuthenticateUserResponse registerAdmin(RegisterUserRequest request) {
        var user = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Roles.ADMIN)
                .build();
        user = userRepo.save(user);
        return AuthenticateUserResponse.builder()
                .Status("Registered Successfully")
                .build();
    }


    public AuthenticateUserResponse signInUser(AuthenticateUserRequest authenticateUserRequest) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (
                                authenticateUserRequest.getEmail(),
                                authenticateUserRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return AuthenticateUserResponse.builder()
                .token(token)
                .build();
    }

    @Transactional(readOnly = true)
    private Users getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(principal.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No User not found with such email"));
    }

}
