package com.student.project.amazone.repo;

import com.student.project.amazone.entity.cartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface CartItemDtoRepository extends CrudRepository<cartItem, Long> {

    @Override
    long count();

    @Query("FROM cartItem c where c.productItem.id = :productId")
    cartItem findByProductId(@Param("productId") Long productId);
}