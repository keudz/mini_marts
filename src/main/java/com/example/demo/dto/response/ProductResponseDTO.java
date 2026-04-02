package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private int id;

    private String nameProduct;

    private String descriptionProduct;

    private double priceProduct;

    private int quantityProduct;

    private String categoryProduct;

    private String imageLink;

    private String subCategoryProduct;

}
