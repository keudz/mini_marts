package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);

    @Transactional
    @Modifying//phương thức có có anocation chỉ đc trả về int or void
    @Query(value = "INSERT INTO User (name,price,category,original_price,stock) values (:name,:price,:category,:original_price,:stock)",nativeQuery = true)
    int inSertProduct(@Param("name") String name, @Param("price") double price, @Param("category") String category, @Param("original_price") double original_price, @Param("stock") int stock);

    //đây là câu truy vấn lấy tat cả product k kể isDelete
    @Query(value = "SELECT p FROM  Product p where p.ID_PRODUCT = :id")
    Product getProductById(@Param("id") int id);

    @Query(value = "SELECT p FROM Product p where p.isDelete = false or p.isDelete is null")
    List<Product> getProductByIsDelete();

}
