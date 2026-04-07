package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_PRODUCT;

    private String name;
    @Column(length = 10000)
    private String description;

    private double price;

    private int stock;
   @Column(name = "original_price")
    private double originalPrice;

    private String category;
//    nhưng Hibernate (công cụ mapping của Spring Boot) có một cơ chế mặc định gọi là Implicit Naming Strategy.
    @Column(name = "image_link")
    private String imagelink;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name ="is_discount")
    private Boolean isDiscount;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart_Iterm> cartItermList;
//    fetch = FetchType.LAZY(Lười biếng): Khi bạn lấy một Product từ database, Hibernate chưa lấy danh sách cartItermList ngay lập tức. Câu lệnh SQL chỉ lấy dữ liệu của bảng product.

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order_Iterm> orderItermList;


}


