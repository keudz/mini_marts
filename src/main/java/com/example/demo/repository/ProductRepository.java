package com.example.demo.repository;

import com.example.demo.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);

    @Transactional
    @Modifying//phương thức có có anocation chỉ đc trả về int or void
    @Query(value = "INSERT INTO User (name,price,category,original_price,stock) values (:name,:price,:category,:original_price,:stock)",nativeQuery = true)
    int inSertProduct(@Param("name") String name, @Param("price") double price, @Param("category") String category, @Param("original_price") double original_price, @Param("stock") int stock);
}
