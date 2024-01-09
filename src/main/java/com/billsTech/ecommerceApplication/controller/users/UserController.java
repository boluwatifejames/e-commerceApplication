package com.billsTech.ecommerceApplication.controller.users;

import com.billsTech.ecommerceApplication.request.AuthenticateUserRequest;
import com.billsTech.ecommerceApplication.request.CustomerRequest;
import com.billsTech.ecommerceApplication.request.DeliveryDetailsRequest;
import com.billsTech.ecommerceApplication.request.RegisterUserRequest;
import com.billsTech.ecommerceApplication.response.*;
import com.billsTech.ecommerceApplication.service.LoginAndSignUp_Service;
import com.billsTech.ecommerceApplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final LoginAndSignUp_Service loginAndSignUpService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticateUserResponse> registerUser(@RequestBody RegisterUserRequest registerRequest) {
        return ResponseEntity.ok(loginAndSignUpService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateUserResponse> signInUser(@RequestBody AuthenticateUserRequest authenticateUserRequest) {
        return ResponseEntity.ok(loginAndSignUpService.signInUser(authenticateUserRequest));
    }

    @PostMapping("/Admin/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(userService.addToCart(customerRequest));
    }

    @PostMapping("/Admin/deliveryDetail")
    public void setDeliveryDetails(@RequestBody DeliveryDetailsRequest deliveryDetails){
        userService.setDeliveryDetails(deliveryDetails);
    }

    @GetMapping("/Admin/viewCart")
    public ResponseEntity<?> viewCart(){
       return ResponseEntity.ok(userService.viewCart());
    }

    @GetMapping("/Admin/checkout")
    public ResponseEntity<?> checkout(@RequestParam Long userId){
        return ResponseEntity.ok(userService.checkout(userId));
    }
}