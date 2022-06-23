package com.student.project.amazone.repo;

import com.student.project.amazone.entity.cartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Cart_modelRepository extends JpaRepository<cartModel, Long> {

    @Query("FROM cartModel c where c.userId.id = :userId")
    cartModel findCart_modelByUserId(@Param("userId") Long userId);

}