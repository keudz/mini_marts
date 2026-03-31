package com.example.demo.service;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.request.UpdateProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.List;

public interface AdminService {
  List<UserResponDTO> showAllUser();

  void blockUser(int id);

  void unlockUser(int id);

  Product addProduct(ProductRequestDTO productRequestDTO);

  Product updateProduct(UpdateProductRequestDTO updateProductRequestDTO);

    Product deleteProduct(int id);

    List<ProductResponseDTO> showAllProduct();
}

