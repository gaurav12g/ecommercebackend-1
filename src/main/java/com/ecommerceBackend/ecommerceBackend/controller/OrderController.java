package com.ecommerceBackend.ecommerceBackend.controller;

import com.ecommerceBackend.ecommerceBackend.exception.OrderException;
import com.ecommerceBackend.ecommerceBackend.exception.UserException;
import com.ecommerceBackend.ecommerceBackend.model.Address;
import com.ecommerceBackend.ecommerceBackend.model.Order;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.service.OrderService;
import com.ecommerceBackend.ecommerceBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order>createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization")String jwt)throws UserException{
        User user=userService.findUserProfileByJwt(jwt);
        Order order=orderService.createOrder(user,shippingAddress);
        System.out.println("order" +order);
        return  new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    private ResponseEntity<List<Order>>usersOrderHistory(@RequestHeader("Authorization")String jwt)throws UserException{
         User user=userService.findUserProfileByJwt(jwt);
         List<Order>orders=orderService.usersOrderHistory(user.getId());
         return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order>findOrderById(@PathVariable("id") Long orderId,@RequestHeader("Authorization")String jwt)throws UserException, OrderException {
        User user=userService.findUserProfileByJwt(jwt);
        Order orders=orderService.findOrderById(orderId);
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);

    }

}
