package com.ecommerceBackend.ecommerceBackend.service;

import com.ecommerceBackend.ecommerceBackend.exception.ProductException;
import com.ecommerceBackend.ecommerceBackend.model.Cart;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.request.AddItemRequest;
import com.ecommerceBackend.ecommerceBackend.response.CartResponse;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest req)throws ProductException;
    public Cart findUserCart(Long userId);

}
