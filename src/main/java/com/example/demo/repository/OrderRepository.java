package com.example.demo.repository;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT o FROM Orders o where o.ID_ORDER =:id")
    Orders findByIdOrder(@RequestParam("id") int id);

    @Query("SELECT SUM(o.TOTAL_AMOUNT) FROM Orders o WHERE o.STATUS = 'COMPLETED' AND (o.isDelete = false OR o.isDelete IS NULL)")
    Double calculateTotalRevenue();

    @Query("SELECT COUNT(o) FROM Orders o WHERE o.STATUS = 'COMPLETED' AND (o.isDelete = false OR o.isDelete IS NULL)")
    Integer countCompletedOrders();

    @Query("SELECT SUM(oi.QUANTITY) FROM Orders o JOIN o.orderItermList oi WHERE o.STATUS = 'COMPLETED' AND (o.isDelete = false OR o.isDelete IS NULL)")
    Integer countTotalProductsSold();

    @Query("SELECT o.STATUS, COUNT(o) FROM Orders o WHERE (o.isDelete = false OR o.isDelete IS NULL) GROUP BY o.STATUS")
    List<Object[]> countOrdersByStatus();
}
