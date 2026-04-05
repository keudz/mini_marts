package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@Entity
@NoArgsConstructor
@Table(name = "cart_items")
public class Cart_Iterm{

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


    @Override
    public boolean equals(Object o) {
        // 1. Kiểm tra cùng địa chỉ
        if (this == o) return true;

        // 2. Kiểm tra null và kiểu lớp
        if (o == null || getClass() != o.getClass()) return false;

        // 3. Ép kiểu
        Cart_Iterm that = (Cart_Iterm) o;

        // 4. Kiểm tra null an toàn trước khi lấy tên Product
        String thisName = this.getProduct() != null ? this.getProduct().getName() : null;
        String thatName = that.getProduct() != null ? that.getProduct().getName() : null;

        return Objects.equals(thisName, thatName);
    }

    @Override
    public int hashCode() {
        // Phải ghi đè hashCode đi kèm với equals
        String name = getProduct() != null ? getProduct().getName() : null;
        return Objects.hash(name);
    }

}
