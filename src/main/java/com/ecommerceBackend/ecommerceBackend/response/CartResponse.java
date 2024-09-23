package com.ecommerceBackend.ecommerceBackend.response;

import com.ecommerceBackend.ecommerceBackend.model.CartItem;
import com.ecommerceBackend.ecommerceBackend.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Long id;
    private User user;
    private List<CartItem> cartItems=new ArrayList<>();

    private double totalPrice;

    private int totalItem;

    private int totalDiscountedPrice;

    private int discounte;
}
