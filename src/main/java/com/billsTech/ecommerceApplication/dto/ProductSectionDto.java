package com.billsTech.ecommerceApplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductSectionDto {
    private String productSection;
    private String productType;
    private List<ProductSubSection> productSubSections;
}
