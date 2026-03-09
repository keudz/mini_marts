package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_ORDER;

    private String DESCRIPTION;

    private String STATUS;

    private String TATAL_AMOUNT;

    private int USER_ID;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order_Iterm> orderItermList;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    public Orders() {
    }

    public Orders(int ID_ORDER, String DESCRIPTION, String STATUS, int USER_ID, String TATAL_AMOUNT) {
        this.ID_ORDER = ID_ORDER;
        this.DESCRIPTION = DESCRIPTION;
        this.STATUS = STATUS;
        this.USER_ID = USER_ID;
        this.TATAL_AMOUNT = TATAL_AMOUNT;
    }

    public int getID_ORDER() {
        return ID_ORDER;
    }

    public void setID_ORDER(int ID_ORDER) {
        this.ID_ORDER = ID_ORDER;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTATAL_AMOUNT() {
        return TATAL_AMOUNT;
    }

    public void setTATAL_AMOUNT(String TATAL_AMOUNT) {
        this.TATAL_AMOUNT = TATAL_AMOUNT;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public List<Order_Iterm> getOrderItermList() {
        return orderItermList;
    }

    public void setOrderItermList(List<Order_Iterm> orderItermList) {
        this.orderItermList = orderItermList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
