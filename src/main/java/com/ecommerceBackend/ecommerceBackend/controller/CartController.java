package com.ecommerceBackend.ecommerceBackend.controller;

import com.ecommerceBackend.ecommerceBackend.exception.ProductException;
import com.ecommerceBackend.ecommerceBackend.exception.UserException;
import com.ecommerceBackend.ecommerceBackend.model.Cart;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.request.AddItemRequest;
import com.ecommerceBackend.ecommerceBackend.response.ApiResponse;
import com.ecommerceBackend.ecommerceBackend.response.CartResponse;
import com.ecommerceBackend.ecommerceBackend.service.CartService;
import com.ecommerceBackend.ecommerceBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Cart>findUserCart(@RequestHeader("Authorization")String jwt)throws UserException{
        User user =userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    private ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization")String jwt)throws UserException, ProductException{
        User user=userService.findUserProfileByJwt(jwt);

        cartService.addCartItem(user.getId(),req);
        ApiResponse res=new ApiResponse();
        res.setMessage("item added to cart");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
}
