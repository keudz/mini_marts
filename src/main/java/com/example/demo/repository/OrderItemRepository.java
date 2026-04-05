package com.example.demo.repository;

import com.example.demo.entity.Order_Iterm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<Order_Iterm, Integer> {
}
