package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart_Iterm> cartItermList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order_Iterm> orderItermList;


    public Product() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getID_PRODUCT() {
        return ID_PRODUCT;
    }

    public void setID_PRODUCT(int ID_PRODUCT) {
        this.ID_PRODUCT = ID_PRODUCT;
    }

    public List<Cart_Iterm> getCartItermList() {
        return cartItermList;
    }

    public void setCartItermList(List<Cart_Iterm> cartItermList) {
        this.cartItermList = cartItermList;
    }

    public List<Order_Iterm> getOrderItermList() {
        return orderItermList;
    }

    public void setOrderItermList(List<Order_Iterm> orderItermList) {
        this.orderItermList = orderItermList;
    }
}


