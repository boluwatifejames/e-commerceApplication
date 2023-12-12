package com.billsTech.ecommerceApplication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerRequest {
    private String productName;
}
