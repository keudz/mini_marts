package com.example.demo.repository;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT o FROM Orders o where o.ID_ORDER =:id")
    Orders findByIdOrder(@RequestParam("id") int id);


}
