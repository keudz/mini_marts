package com.example.demo.service;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Product;

import java.util.List;

public interface AdminService {
  List<UserResponDTO> showAllUser();

  void blockUser(int id);

  void unlockUser(int id);

  Product addProduct(ProductRequestDTO productRequestDTO);

  void updateProduct(ProductRequestDTO updateProductRequestDTO);

    Product deleteProduct(int id);

    List<ProductResponseDTO> showAllProduct();
}

