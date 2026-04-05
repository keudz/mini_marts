package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_CART;


    @OneToOne
    @JoinColumn(name = "ID_USER")
    //@JoinColumn chỉ định cột khoá ngoại của entity chứa khoá ngoại
    private User user;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY ,  orphanRemoval = true)
    //orphanRemoval = true là một thuộc tính trong quan hệ @OneToMany hoặc @OneToOne của JPA/Hibernate,
    //giúp tự động xóa bản ghi "mồ côi"(orphan) khỏi database nếu nó bị xóa khỏi danh sách trong Java.
    private List<Cart_Iterm> cartItermList = new ArrayList<>();

}
