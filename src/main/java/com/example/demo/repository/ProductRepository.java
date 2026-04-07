package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);

    @Transactional
    @Modifying//phương thức có có anocation chỉ đc trả về int or void
//    @Query(value = "INSERT INTO User (name,price,category,original_price,stock) values (:name,:price,:category,:original_price,:stock)",nativeQuery = true)
//    int inSertProduct(@Param("name") String name, @Param("price") double price, @Param("category") String category, @Param("original_price") double original_price, @Param("stock") int stock);

    //đây là câu truy vấn lấy tat cả product k kể isDelete
//    @Query(value = "SELECT p FROM  Product p where p.ID_PRODUCT = :id")
//    Product getProductById(@Param("id") int id);

    @Query(value = "SELECT p FROM Product p where p.isDelete = false or p.isDelete is null")
    List<Product> getProductByIsDelete();

    @Query("SELECT p FROM Product p WHERE p.stock < 10 AND (p.isDelete = false OR p.isDelete IS NULL)")
    List<Product> getLowStockProducts();

    @Query("SELECT p FROM Product p JOIN p.orderItermList oi JOIN oi.order o WHERE o.STATUS = 'COMPLETED' AND (p.isDelete = false OR p.isDelete IS NULL) GROUP BY p.ID_PRODUCT ORDER BY SUM(oi.QUANTITY) DESC")
    List<Product> getTopSellingProducts(org.springframework.data.domain.Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN Order_Iterm oi ON p.ID_PRODUCT = oi.product.ID_PRODUCT " +
            "WHERE p.createAt < :dateThreshold " +
            "AND (p.isDelete = false OR p.isDelete IS NULL) " +
            "AND (p.isDiscount is NULL )" +
            "GROUP BY p.ID_PRODUCT " +
            "HAVING COALESCE(SUM(oi.QUANTITY), 0) < 20")
    List<Product> findOldProductsForDiscount(@Param("dateThreshold") LocalDate dateThreshold);



    @Query("SELECT p, SUM(oi.QUANTITY) FROM Product p " +
            "JOIN p.orderItermList oi " +
            "JOIN oi.order o " +
            "WHERE o.STATUS = 'COMPLETED' " +
            "AND (p.isDelete = false OR p.isDelete IS NULL) " +
            "GROUP BY p.ID_PRODUCT " +
            "ORDER BY SUM(oi.QUANTITY) DESC")
    List<Object[]> getTopSellingProductsWithSales(Pageable pageable);
}
