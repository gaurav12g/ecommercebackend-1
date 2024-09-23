package com.ecommerceBackend.ecommerceBackend.controller;

import com.ecommerceBackend.ecommerceBackend.exception.OrderException;
import com.ecommerceBackend.ecommerceBackend.model.Order;

import com.ecommerceBackend.ecommerceBackend.response.ApiResponse;
import com.ecommerceBackend.ecommerceBackend.service.OrderService;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>>getAllOrdersHandler(){
        List<Order>order=orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(order, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order>ConfirmedOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws OrderException{
        Order order=orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order>ShippedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt)throws OrderException{
        Order order=orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order>DeliveredOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws OrderException{
        Order order=orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order>CancelOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws OrderException{
        Order order=orderService.cancledOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse>DeleteOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws OrderException{
        orderService.deleteOrder(orderId);
        ApiResponse res=new ApiResponse();
        res.setMessage("order deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
