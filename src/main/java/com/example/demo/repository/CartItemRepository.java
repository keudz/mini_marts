package com.example.demo.repository;

import com.example.demo.entity.Cart_Iterm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<Cart_Iterm,Integer> {

}
