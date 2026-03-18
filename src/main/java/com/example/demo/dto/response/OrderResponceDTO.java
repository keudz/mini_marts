package com.example.demo.dto.response;

import java.util.List;

public class OrderResponceDTO {
        private String email;
        private List<String> listProduct;

        public OrderResponceDTO(List<String> listProduct, String email) {
            this.listProduct = listProduct;
            this.email = email;
        }

        public OrderResponceDTO() {
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

