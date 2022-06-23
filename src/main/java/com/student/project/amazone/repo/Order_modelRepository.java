package com.student.project.amazone.repo;

import com.student.project.amazone.dto.Order_modelInfo;
import com.student.project.amazone.entity.Order_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Order_modelRepository extends JpaRepository<Order_model, Long> {
    @Query("FROM Order_model c where c.userId.id = :userId")
    List<Order_model> findByUserId(@Param("userId") Long userId);

    @Query(value = "select *from order_model WHERE fk_user_id =:userId ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Order_model> findTop3LastOrderByUserId(@Param("userId") Long userId);


    @Query(value = "\n" +
            "SELECT \n" +
            "    SUM(IF(month = 'Jan', total, 0)) AS 'Jan',\n" +
            "    SUM(IF(month = 'Feb', total, 0)) AS 'Feb',\n" +
            "    SUM(IF(month = 'Mar', total, 0)) AS 'Mar',\n" +
            "    SUM(IF(month = 'Apr', total, 0)) AS 'Apr',\n" +
            "    SUM(IF(month = 'May', total, 0)) AS 'May',\n" +
            "    SUM(IF(month = 'Jun', total, 0)) AS 'Jun',\n" +
            "    SUM(IF(month = 'Jul', total, 0)) AS 'Jul',\n" +
            "    SUM(IF(month = 'Aug', total, 0)) AS 'Aug',\n" +
            "    SUM(IF(month = 'Sep', total, 0)) AS 'Sep',\n" +
            "    SUM(IF(month = 'Oct', total, 0)) AS 'Oct',\n" +
            "    SUM(IF(month = 'Nov', total, 0)) AS 'Nov',\n" +
            "    SUM(IF(month = 'Dec', total, 0)) AS 'Dec',\n" +
            "    SUM(total) AS total_yearly\n" +
            "    FROM (\n" +
            "SELECT DATE_FORMAT(last_updated, \"%b\") AS month, SUM(total_amount) as total\n" +
            "FROM order_model\n" +
            "WHERE last_updated <= NOW() and last_updated >= Date_add(Now(),interval - 12 month) and status = 4 and fk_user_id = :userId\n" +
            "GROUP BY DATE_FORMAT(last_updated, \"%m-%Y\")) as sub", nativeQuery = true)
    List<Order_modelInfo> getValueIn12Month(@Param("userId") long userId);
}