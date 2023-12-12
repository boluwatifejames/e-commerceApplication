package com.billsTech.ecommerceApplication.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductsDto {
   List<ProductSectionDto> products;
}
