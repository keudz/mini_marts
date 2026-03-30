package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_ORDER;

    private String DESCRIPTION;

    private String STATUS;

    private double TATAL_AMOUNT;

    private int USER_ID;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order_Iterm> orderItermList;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    public Orders() {
    }

    public Orders(int ID_ORDER, String DESCRIPTION, String STATUS, int USER_ID, double TATAL_AMOUNT) {
        this.ID_ORDER = ID_ORDER;
        this.DESCRIPTION = DESCRIPTION;
        this.STATUS = STATUS;
        this.USER_ID = USER_ID;
        this.TATAL_AMOUNT = TATAL_AMOUNT;
    }

}
