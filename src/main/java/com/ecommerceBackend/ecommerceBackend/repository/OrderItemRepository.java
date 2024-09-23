package com.ecommerceBackend.ecommerceBackend.repository;

import com.ecommerceBackend.ecommerceBackend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
