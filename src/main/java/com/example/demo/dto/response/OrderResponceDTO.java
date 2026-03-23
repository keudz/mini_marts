package com.example.demo.dto.response;

import com.example.demo.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class OrderResponceDTO {

   private String des;

   private String status;

   private double total_amount;

   private List<ProductResponseDTO> product;


}

