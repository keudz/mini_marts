package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
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


    private List<Cart_Iterm> cartItermList;

    public Cart() {
    }

    public Cart(int ID_CART) {
        this.ID_CART = ID_CART;
    }

    public int getID_CART() {
        return ID_CART;
    }

    public void setID_CART(int ID_CART) {
        this.ID_CART = ID_CART;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Cart_Iterm> getCartItermList() {
        return cartItermList;
    }

    public void setCartItermList(List<Cart_Iterm> cartItermList) {
        this.cartItermList = cartItermList;
    }
}
