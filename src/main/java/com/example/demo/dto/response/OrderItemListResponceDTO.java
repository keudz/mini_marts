package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemListResponceDTO {
    private int productId;
    
    private String nameProduct;

    private int quantity;

    private double price;

    private int stock;

    private String category;

    private String description;

    private String imagelink;
}
