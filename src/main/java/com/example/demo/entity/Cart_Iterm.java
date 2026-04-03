package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        // 1. Kiểm tra cùng địa chỉ
        if (this == o) return true;

        // 2. Kiểm tra null và kiểu lớp
        if (o == null || getClass() != o.getClass()) return false;

        // 3. Ép kiểu
        Cart_Iterm that = (Cart_Iterm) o;

        // 4. So sánh giá trị (Sử dụng Objects.equals để chống lỗi Null)
        // Giả sử hai item bằng nhau nếu tên sản phẩm giống nhau
        return Objects.equals(this.getProduct().getName(), that.getProduct().getName());
    }

    @Override
    public int hashCode() {
        // Phải ghi đè hashCode đi kèm với equals
        return Objects.hash(getProduct().getName());
    }

}
