package com.ecommerceBackend.ecommerceBackend.service;

import com.ecommerceBackend.ecommerceBackend.exception.CartItemException;
import com.ecommerceBackend.ecommerceBackend.exception.UserException;
import com.ecommerceBackend.ecommerceBackend.model.Cart;
import com.ecommerceBackend.ecommerceBackend.model.CartItem;
import com.ecommerceBackend.ecommerceBackend.model.Product;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.repository.CartItemRepository;
import com.ecommerceBackend.ecommerceBackend.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService{

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    public  CartItemServiceImplementation(CartItemRepository cartItemRepository,UserService userService,CartRepository cartRepository){
        this.cartRepository=cartRepository;
        this.userService=userService;
        this.cartItemRepository=cartItemRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()* cartItem.getQuantity());

        CartItem createdCartItem=cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(id);
        User user=userService.findUserById(item.getUserId());
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem=cartItemRepository.isCartItemExist(cart,product,size,userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem=findCartItemById(cartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }
        else{
           throw new UserException("You can't remove another user cart item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem>opt=cartItemRepository.findById(cartItemId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new CartItemException("Cart item not found with id: "+cartItemId);
    }

    @Override
    public List<CartItem> findByCartItemByCartId(Long cartId) throws CartItemException {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        if (cartItems == null || cartItems.isEmpty()) {
            throw new CartItemException("No cart items found for Cart ID: " + cartId);
        }

        return cartItems;
    }
}
