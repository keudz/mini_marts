package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_PRODUCT;

    private String name;

    private String description;

    private double price;

    private int stock;

    private double original_price;

    private String category;
//    nhưng Hibernate (công cụ mapping của Spring Boot) có một cơ chế mặc định gọi là Implicit Naming Strategy.
    @Column(name = "image_link")
    private String imagelink;

    @Column(name = "sub_category")
    private String subCategory;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart_Iterm> cartItermList;
//    fetch = FetchType.LAZY(Lười biếng): Khi bạn lấy một Product từ database, Hibernate chưa lấy danh sách cartItermList ngay lập tức. Câu lệnh SQL chỉ lấy dữ liệu của bảng product.

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order_Iterm> orderItermList;


    public Product() {};


}


