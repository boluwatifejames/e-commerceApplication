package com.billsTech.ecommerceApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DeliveryDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long   id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String phoneNumber;
    private String backupPhoneNumber;
    private String emailAddress;
    private String country;
    private String state;
    private String town;
    private String postalCode;
    private String localGovernmentArea;
    private String houseAddress;
}
