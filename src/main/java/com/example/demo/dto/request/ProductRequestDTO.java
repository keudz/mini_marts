package com.example.demo.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data // Tự động tạo Getter, Setter, toString, equals, hashCode
@AllArgsConstructor // Tạo Constructor full tham số
@NoArgsConstructor // Tạo Constructor không tham số
public class ProductRequestDTO {

    private int idProduct;

    private String name;

    private String description;

    private double price;

    private int stock;

    private double originalPrice;

    private String category;

    private String imagelink;

    private String subCategory;
}