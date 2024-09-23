package com.ecommerceBackend.ecommerceBackend.repository;

import com.ecommerceBackend.ecommerceBackend.model.Cart;
import com.ecommerceBackend.ecommerceBackend.model.CartItem;
import com.ecommerceBackend.ecommerceBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT ci From CartItem ci Where ci.cart=:cart And ci.product=:product And ci.size=:size And ci.userId=:userId")
    public CartItem isCartItemExist(@Param("cart")Cart cart, @Param("product")Product product, @Param("size")String size,@Param("userId")Long userId );

    List<CartItem> findByCartId(Long cartId);
}
