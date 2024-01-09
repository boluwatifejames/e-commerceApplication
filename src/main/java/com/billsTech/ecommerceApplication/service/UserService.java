package com.billsTech.ecommerceApplication.service;

import com.billsTech.ecommerceApplication.entity.Customer;
import com.billsTech.ecommerceApplication.entity.DeliveryDetails;
import com.billsTech.ecommerceApplication.entity.Products;
import com.billsTech.ecommerceApplication.repositories.CustomerRepository;
import com.billsTech.ecommerceApplication.repositories.DeliveryDetailsRepo;
import com.billsTech.ecommerceApplication.repositories.ProductRepository;
import com.billsTech.ecommerceApplication.request.CustomerRequest;
import com.billsTech.ecommerceApplication.request.DeliveryDetailsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
        private final DeliveryDetailsRepo deliveryDetailsRepo;
        private final ProductRepository productRepository;
        private final CustomerRepository customerRepository;

    public String addToCart(CustomerRequest customerRequest) {
        //LOGIC to add to cart
        Customer req = new Customer();
        Products productExists = productRepository.findByProductName(customerRequest.getProductName());
        if(productExists ==null){
            throw new IllegalArgumentException("invalid Product Name");
        }
        req.setProductName(customerRequest.getProductName());
        req.setProductPrice(productExists.getProductPrice());
        req.setProductDescription(productExists.getProductDescription());
        customerRepository.save(req);
        return "ok";
    }

    public Object viewCart() {
        List<Customer> allOrders = customerRepository.findAll();
       return allOrders;
    }

    public Object checkout(Long userId) {
       DeliveryDetails details = deliveryDetailsRepo.findByuserId(userId);
        List<Customer> allOrders = customerRepository.findAll();
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<Map<String, Object>> formattedOrders = new ArrayList<>();

        for (Customer cust : allOrders) {
            Map<String, Object> orderDetails = new HashMap<>();
            orderDetails.put("productName", cust.getProductName());
            orderDetails.put("productPrice", cust.getProductPrice());
            orderDetails.put("productDescription", cust.getProductDescription());

            formattedOrders.add(orderDetails);

            totalPrice = totalPrice.add(cust.getProductPrice());

        }
        Map<String, Object> result = new HashMap<>();
        result.put("orders", formattedOrders);
        result.put("totalPrice", totalPrice);
        if(details!=null) {
            result.put("address", details.getHouseAddress());
            result.put("phoneNumber", details.getPhoneNumber());
            result.put("emailAddress", details.getEmailAddress());
        }
        return result;
    }

    public void setDeliveryDetails(DeliveryDetailsRequest deliveryDetails) {
        DeliveryDetails details = new DeliveryDetails();
        details.setFirstName(deliveryDetails.getFirstName());
        details.setPhoneNumber(deliveryDetails.getPhoneNumber());
        details.setBackupPhoneNumber(deliveryDetails.getBackupPhoneNumber());
        details.setEmailAddress(deliveryDetails.getEmailAddress());
        details.setCountry(deliveryDetails.getCountry());
        details.setState(deliveryDetails.getState());
        details.setTown(deliveryDetails.getTown());
        details.setPostalCode(deliveryDetails.getPostalCode());
        details.setLocalGovernmentArea(deliveryDetails.getLocalGovernmentArea());
        details.setHouseAddress(deliveryDetails.getHouseAddress());
        deliveryDetailsRepo.save(details);
    }
}
