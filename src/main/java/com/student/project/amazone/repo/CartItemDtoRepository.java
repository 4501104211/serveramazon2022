package com.student.project.amazone.repo;

import com.student.project.amazone.entity.cartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemDtoRepository extends JpaRepository<cartItem, Long> {
    @Query("FROM cartItem c where c.productItem.id = :productId")
    cartItem findByProductId(@Param("productId") Long productId);
}