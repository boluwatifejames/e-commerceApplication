package com.billsTech.ecommerceApplication.repositories;

import com.billsTech.ecommerceApplication.entity.DeliveryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryDetailsRepo extends JpaRepository<DeliveryDetails,Long> {
   DeliveryDetails findByuserId(Long id);
}
