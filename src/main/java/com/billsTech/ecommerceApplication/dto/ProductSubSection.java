package com.billsTech.ecommerceApplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSubSection {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
