package com.billsTech.ecommerceApplication.controller.Admin;

import com.billsTech.ecommerceApplication.dto.ProductSectionDto;
import com.billsTech.ecommerceApplication.dto.ProductsDto;
import com.billsTech.ecommerceApplication.request.RegisterUserRequest;
import com.billsTech.ecommerceApplication.response.AuthenticateUserResponse;
import com.billsTech.ecommerceApplication.service.AdminService;
import com.billsTech.ecommerceApplication.service.LoginAndSignUp_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final LoginAndSignUp_Service loginAndSignUpService;
    private final AdminService adminService;


    @PostMapping("/Admin/register")
    public ResponseEntity<AuthenticateUserResponse> registerAdmin(@RequestBody RegisterUserRequest registerRequest) {
        return ResponseEntity.ok(loginAndSignUpService.registerAdmin(registerRequest));
    }

    @PostMapping("/Admin/addProducts")
    public ResponseEntity<?> addProducts(@RequestBody ProductsDto productsDto) {
        return ResponseEntity.ok( adminService.addProducts(productsDto));
    }

    @GetMapping("/Admin/getProductSection")
    public ResponseEntity<?> getProductSection(@RequestParam String productSection){
      return ResponseEntity.ok(adminService.getProductSection(productSection));
    }


}
