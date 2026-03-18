package com.example.demo.entity;

import jakarta.persistence.*;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getORIGINAL_PRICE() {
        return ORIGINAL_PRICE;
    }

    public void setORIGINAL_PRICE(double ORIGINAL_PRICE) {
        this.ORIGINAL_PRICE = ORIGINAL_PRICE;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
