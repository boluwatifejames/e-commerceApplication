package com.billsTech.ecommerceApplication.repositories;

import com.billsTech.ecommerceApplication.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
   List<Products> findByProductSection(String productSection);
   Products findByProductSectionAndProductTypeAndProductName(String productSection , String productType,String productName);
   List<Products> findByProductSectionContaining(String productSection);
   List<Products> findByProductSectionAndProductType(String productSection, String productType);

   // Other custom queries if needed
   Products findByProductName(String productName);
}