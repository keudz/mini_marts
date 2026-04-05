package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_ORDER;

    private String DESCRIPTION;
    private String STATUS;
    private double TOTAL_AMOUNT;

    @Column(name = "is_delete")
    private Boolean isDelete = false; // Nên set mặc định là false

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Order_Iterm> orderItermList = new ArrayList<>(); // Khởi tạo sẵn tránh NullPointerException

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;
}