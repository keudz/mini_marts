package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int stock;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String category;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String description;

    private String imagelink;
}
