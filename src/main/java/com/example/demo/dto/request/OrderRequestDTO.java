package com.example.demo.dto.request;

import java.util.List;

public class OrderRequestDTO {
    private String email;
    private List<String> listProduct;

    public OrderRequestDTO(List<String> listProduct, String email) {
        this.listProduct = listProduct;
        this.email = email;
    }

    public OrderRequestDTO() {
    }

    public List<String> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<String> listProduct) {
        this.listProduct = listProduct;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}