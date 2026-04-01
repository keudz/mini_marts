package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private int id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private String category;

    private String imageLink;

    private String subCategory;

}
