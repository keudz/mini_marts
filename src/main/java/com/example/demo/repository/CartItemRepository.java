package com.example.demo.repository;

import com.example.demo.entity.Cart_Iterm;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Iterm, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart_Iterm c WHERE c.cart.ID_CART = :idCart")
    void deleteAllByCartId(@Param("idCart") int idCart);



    @Query("SELECT c FROM Cart_Iterm c WHERE c.product.ID_PRODUCT = :id_product")
     Cart_Iterm seletByIdProduct(@Param("id_product") int id_product);
}

