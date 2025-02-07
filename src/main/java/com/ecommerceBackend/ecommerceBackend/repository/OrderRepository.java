package com.ecommerceBackend.ecommerceBackend.repository;

import com.ecommerceBackend.ecommerceBackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o Where o.user.id=:userId AND (o.orderStatus= 'PLACED' OR o.orderStatus='CONFIRMED' OR o.orderStatus='SHIPPED' OR o.orderStatus='DELIVERED') ")
    public List<Order>getUserOrders(@Param("userId")Long userId);
}
