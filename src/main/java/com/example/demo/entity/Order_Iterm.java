package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class Order_Iterm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private double ORIGINAL_PRICE;

    private double PRICE;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ID_ORDER")
    private Orders order;

    public Order_Iterm() {
    }

    public Order_Iterm(int id, int ORIGINAL_PRICE, int PRICE) {
        this.id = id;
        this.ORIGINAL_PRICE = ORIGINAL_PRICE;
        this.PRICE = PRICE;
    }


}
