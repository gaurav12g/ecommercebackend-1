package com.ecommerceBackend.ecommerceBackend.model;


import jakarta.persistence.Column;

import java.time.LocalDateTime;


public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String cardholderName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    @Column(name = "cvv")
    private String cvv;

}
