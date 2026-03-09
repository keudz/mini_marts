package com.example.demo.service;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    Object login(UserLoginRequestDTO user);

    UserCreateResponseDTO createUser (UserCreateRequestDTO user);

    UserCreateResponseDTO getUserById(int id);

    List<ProductResponseDTO> showallproduct();

    Object addProductToCart(UserLoginRequestDTO user,String nameProduct,int quantity);

    Object userCheckListProduct(UserLoginRequestDTO user);

    Object userDeleteProduct(UserLoginRequestDTO user,String nameProduct);

}
