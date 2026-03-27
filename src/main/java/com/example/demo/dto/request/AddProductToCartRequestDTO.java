package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;


public class AddProductToCartRequestDTO {
    private String email;

    private String nameProduct ;

    private int quantity;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
