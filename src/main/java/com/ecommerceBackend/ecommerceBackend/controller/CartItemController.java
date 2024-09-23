package com.ecommerceBackend.ecommerceBackend.controller;


import com.ecommerceBackend.ecommerceBackend.exception.CartItemException;
import com.ecommerceBackend.ecommerceBackend.exception.UserException;
import com.ecommerceBackend.ecommerceBackend.model.CartItem;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.response.ApiResponse;
import com.ecommerceBackend.ecommerceBackend.service.CartItemService;
import com.ecommerceBackend.ecommerceBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItem(@RequestHeader("Authorization")String jwt,@PathVariable Long cartItemId)throws UserException, CartItemException{
        User user=userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);
        ApiResponse res=new ApiResponse();
        res.setMessage("item delete successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem>updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
                                                  @RequestHeader("Authorization")String jwt)throws UserException, CartItemException{
        User user=userService.findUserProfileByJwt(jwt);
        CartItem updateCartItem=cartItemService.updateCartItem(user.getId(),cartItemId,cartItem);
        return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
    }
}
