package com.billsTech.ecommerceApplication.request;

import lombok.Data;

@Data
public class DeliveryDetailsRequest {
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
