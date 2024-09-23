package com.ecommerceBackend.ecommerceBackend.service;


import com.ecommerceBackend.ecommerceBackend.exception.CartItemException;
import com.ecommerceBackend.ecommerceBackend.exception.ProductException;
import com.ecommerceBackend.ecommerceBackend.model.Cart;
import com.ecommerceBackend.ecommerceBackend.model.CartItem;
import com.ecommerceBackend.ecommerceBackend.model.Product;
import com.ecommerceBackend.ecommerceBackend.model.User;

import com.ecommerceBackend.ecommerceBackend.repository.CartRepository;
import com.ecommerceBackend.ecommerceBackend.request.AddItemRequest;

import com.ecommerceBackend.ecommerceBackend.response.CartResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CartServiceImplementation implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;


    public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }


    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException{
            Cart cart = cartRepository.findByUserId(userId);
            Product product = productService.findProductById(req.getProductId());
            CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
            if (isPresent == null) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setCart(cart);
                cartItem.setQuantity(req.getQuantity());
                cartItem.setUserId(userId);
                int price = req.getQuantity() * product.getDiscountedPrice();
                cartItem.setPrice(price);
                cartItem.setSize(req.getSize());
                CartItem createdCartItem = cartItemService.createCartItem(cartItem);
                cart.getCartItems().add(createdCartItem);
            }

            return "Hello";
}
@Override
    public Cart findUserCart(Long userId) {
        Cart cart;
        try {
            cart = cartRepository.findByUserId(userId);

            if (cart == null) {
                throw new CartItemException("No cart found for User ID: " + userId);
            }

            int totalPrice = 0;
            int totalDiscountedPrice = 0;
            int totalItem = 0;

            List<CartItem> cartItems = cartItemService.findByCartItemByCartId(cart.getId());

            for (CartItem cartItem : cartItems) {
                totalPrice += cartItem.getPrice();
                totalDiscountedPrice += cartItem.getDiscountedPrice();
                totalItem += cartItem.getQuantity();
            }

            cart.setTotalDiscountedPrice(totalDiscountedPrice);
            cart.setTotalItem(totalItem);
            cart.setTotalPrice(totalPrice);
            cart.setDiscounte(totalPrice - totalDiscountedPrice);

            cartRepository.save(cart);
             System.out.println(cart.getUser());
            // Create CartResponse object
//            CartResponse cartResponse = new CartResponse();
//
//            cartResponse.setUser(cart.getUser());
//            cartResponse.setId(cart.getId());
//            cartResponse.setDiscounte(cart.getDiscounte());
//            cartResponse.setTotalItem(cart.getTotalItem());
//            cartResponse.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
//            cartResponse.setTotalPrice(cart.getTotalPrice());
//            cartResponse.getCartItems().addAll(cartItems);
            return cart;

        } catch (Exception e) {
            // Log the error and return an empty CartResponse
            System.err.println("Error occurred: " + e.getMessage());

            // Return an empty CartResponse object
            return new Cart();
        }
    }



}
