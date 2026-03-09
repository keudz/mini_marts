package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class Cart_Iterm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private int QUANTITY;


    @ManyToOne
    @JoinColumn(name = "ID_CART")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;



    public Cart_Iterm() {
    }

    public Cart_Iterm(int ID, int QUANTITY) {
        this.ID = ID;
        this.QUANTITY = QUANTITY;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
