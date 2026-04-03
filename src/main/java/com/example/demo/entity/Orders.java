package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "id_user", insertable = false, updatable = false)
    private int idUser;

    @Column(name =  "is_delete")
    private Boolean isDelete;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order_Iterm> orderItermList;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;



}
