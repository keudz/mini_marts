package com.example.demo.service;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.DashboardResponseDTO;
import com.example.demo.dto.response.OrderResponceDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.List;

public interface AdminService {
  List<UserResponDTO> showAllUser();

  void blockUser(int id);

  void unlockUser(int id);

  Product addProduct(ProductRequestDTO productRequestDTO);

  void updateProduct(ProductRequestDTO updateProductRequestDTO);

   void deleteProduct(int id);

    List<ProductResponseDTO> showAllProduct();

    void redoProduct(int id);

    List<User> getAllUser();

    List<OrderResponceDTO> getAllOrders();

    void setDeLiveringForOrder(int id);

    void setCompletedForOrder(int id);

    DashboardResponseDTO getDashboardMetrics();

    void applySmartDiscount();
}

