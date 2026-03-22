package com.example.demo.service;

import com.example.demo.dto.request.EmailRequest;
import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    UserCreateResponseDTO login(UserLoginRequestDTO user);

    UserCreateResponseDTO registerUser (UserCreateRequestDTO user);

    UserCreateResponseDTO getUserById(int id);

    List<ProductResponseDTO> showallproduct();

    String addProductToCart(String email,String nameProduct,int quantity);

    List<ProductResponseDTO> userCheckListProductInCart(EmailRequest user);

    String userDeleteProductInCart(String email ,String nameProduct);

    Object useOrderAllItemInCartToOrder(String email);

    Object useOrderSomeItemFromCartToOrder(String email,List<String> listProduct );



}
