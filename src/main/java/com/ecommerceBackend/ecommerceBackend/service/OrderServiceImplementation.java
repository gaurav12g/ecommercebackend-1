package com.ecommerceBackend.ecommerceBackend.service;

import com.ecommerceBackend.ecommerceBackend.exception.OrderException;
import com.ecommerceBackend.ecommerceBackend.model.*;
import com.ecommerceBackend.ecommerceBackend.repository.*;
import com.ecommerceBackend.ecommerceBackend.response.CartResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService{

   private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private CartService cartService;

    private OrderItemService orderItemService;
    private UserRepository userRepository;

   public  OrderServiceImplementation(UserRepository userRepository,
                                      CartService cartService,OrderItemService orderItemService,
                                      AddressRepository addressRepository,OrderRepository orderRepository,
                                      OrderItemRepository orderItemRepository){
          this.orderRepository=orderRepository;
          this.cartService=cartService;
         this.orderItemRepository=orderItemRepository;
         this.orderItemService=orderItemService;
         this.userRepository=userRepository;
         this.addressRepository=addressRepository;
    }
    @Override
    public Order createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address address=addressRepository.save(shippingAddress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart=cartService.findUserCart(user.getId());
        List<OrderItem>orderItems=new ArrayList<>();
        for(CartItem item:cart.getCartItems()){
            OrderItem orderItem=new OrderItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createOrderItem=orderItemRepository.save(orderItem);
            orderItems.add(createOrderItem);
        }
        Order createOrder=new Order();
        createOrder.setUser(user);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        createOrder.setTotalDiscountPrice(cart.getTotalDiscountedPrice());
        createOrder.setDiscount(cart.getDiscounte());
        createOrder.setTotalItem(cart.getTotalItem());
        createOrder.setShippingAddress(address);
        createOrder.setOrderDate(LocalDateTime.now());
        createOrder.setOrderStatus("PENDING");
        createOrder.getPaymentDetails().setStatus("PENDING");
        createOrder.setCreatedAt(LocalDateTime.now());

        Order saveOrder=orderRepository.save(createOrder);

        for(OrderItem item: orderItems ){
            item.setOrder(saveOrder);
            orderItemRepository.save(item);
        }

        return saveOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order>opt=orderRepository.findById(orderId);
        if(opt.isPresent()){
            return opt.get();
        }
       throw new OrderException("Order not exist with id "+orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {

       List<Order>orders=orderRepository.getUserOrders(userId);
       return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
       Order order=findOrderById(orderId);
       order.setOrderStatus("PLACED");
       order.getPaymentDetails().setStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
       Order order =findOrderById(orderId);
       order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order =findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order =findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        Order order =findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
          Order order=findOrderById(orderId);
          orderRepository.deleteById(orderId);
    }
}
