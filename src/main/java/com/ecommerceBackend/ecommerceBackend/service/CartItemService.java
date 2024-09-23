package com.ecommerceBackend.ecommerceBackend.service;

import com.ecommerceBackend.ecommerceBackend.exception.CartItemException;
import com.ecommerceBackend.ecommerceBackend.exception.UserException;
import com.ecommerceBackend.ecommerceBackend.model.Cart;
import com.ecommerceBackend.ecommerceBackend.model.CartItem;
import com.ecommerceBackend.ecommerceBackend.model.Product;

import java.util.List;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId,Long id, CartItem cartItem)throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    public void removeCartItem(Long userId, Long cartItemId)throws CartItemException,UserException;

    public CartItem findCartItemById(Long cartItemId)throws CartItemException;

    public List<CartItem> findByCartItemByCartId(Long cartId)throws CartItemException;

}
