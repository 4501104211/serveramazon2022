package com.student.project.amazone.repo;

import com.student.project.amazone.entity.cartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Cart_modelRepository extends JpaRepository<cartModel, Long> {

    @Query(value="SELECT * FROM cart_model c where c.id  in (select fk_cart  from users_model um  where um.id = :userId)",nativeQuery = true)
    cartModel findCartByUserId(@Param("userId") Long userId);


}