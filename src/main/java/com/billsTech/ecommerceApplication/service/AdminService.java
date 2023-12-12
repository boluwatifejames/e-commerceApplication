package com.billsTech.ecommerceApplication.service;

import com.billsTech.ecommerceApplication.dto.ProductSectionDto;
import com.billsTech.ecommerceApplication.dto.ProductSubSection;
import com.billsTech.ecommerceApplication.dto.ProductsDto;
import com.billsTech.ecommerceApplication.entity.Products;
import com.billsTech.ecommerceApplication.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminService {

    private final ProductRepository productRepository;
    public String addProducts(ProductsDto productsDto) {
        for (ProductSectionDto section : productsDto.getProducts()) {
            List<Products> existingProducts = productRepository.findByProductSectionAndProductType(section.getProductSection(), section.getProductType());

            if (existingProducts.isEmpty()) {
                createProductType(section.getProductSection(), section.getProductType(),section.getProductSubSections().get(0));
            }

            addOrUpdateProducts(section);
        }
        log.info(productsDto);
        return "ok";
    }
    private boolean doesProductTypeExist(String productSection, String productType) {
        List<Products> existingProductsByType = productRepository.findByProductSectionAndProductType(productSection, productType);
        return !existingProductsByType.isEmpty();
    }

    private void createProductType(String productSection, String productType,ProductSubSection productSubSection) {

        Products newProductType = new Products();
        newProductType.setProductSection(productSection);
        newProductType.setProductName(productSubSection.getProductName()); // Set a default name or leave it empty as per your logic
        newProductType.setProductDescription(productSubSection.getProductDescription()); // Set a default description or leave it empty as per your logic
        newProductType.setProductPrice(productSubSection.getProductPrice()); // Set a default price or leave it as per your logic
        newProductType.setProductType(productType);
        productRepository.save(newProductType);
    }
    private void addOrUpdateProducts(ProductSectionDto section) {
        boolean productTypeExists = doesProductTypeExist(section.getProductSection(), section.getProductType());

        if (productTypeExists) {
            for (ProductSubSection subSection : section.getProductSubSections()) {
                Products existingProduct = productRepository.findByProductSectionAndProductTypeAndProductName(
                        section.getProductSection(),
                        section.getProductType(),
                        subSection.getProductName()
                );

                if (existingProduct != null) {
                    // Update existing product
                    existingProduct.setProductName(subSection.getProductName());
                    existingProduct.setProductDescription(subSection.getProductDescription());
                    existingProduct.setProductPrice(subSection.getProductPrice());
                    productRepository.save(existingProduct);
                } else {
                    // Create new product
                    Products newProduct = new Products();
                    newProduct.setProductSection(section.getProductSection());
                    newProduct.setProductName(subSection.getProductName());
                    newProduct.setProductDescription(subSection.getProductDescription());
                    newProduct.setProductPrice(subSection.getProductPrice());
                    newProduct.setProductType(section.getProductType());
                    productRepository.save(newProduct);
                }
            }
        } else {
            // Create new product type along with its products
            createProductType(section.getProductSection(), section.getProductType(), section.getProductSubSections().get(0));
        }
    }


    public List<Products> getProductSection(String productSection) {
        return productRepository.findByProductSectionContaining(productSection);
    }
}

