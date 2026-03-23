package com.example.demo.service;

import com.example.demo.dto.request.EmailRequest;
import com.example.demo.dto.request.OrderRequestDTO;
import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;

import java.util.List;

public interface UserService {
    UserCreateResponseDTO login(UserLoginRequestDTO user);

    UserCreateResponseDTO registerUser (UserCreateRequestDTO user);

    UserCreateResponseDTO getUserById(int id);

    List<ProductResponseDTO> showallproduct();

    String addProductToCart(String email,String nameProduct,int quantity);

    List<ProductResponseDTO> userCheckListProductInCart(EmailRequest user);

    String userDeleteProductInCart(String email ,String nameProduct);

    Object useOrderAllItemInCartToOrder(EmailRequest email);

    Object useOrderSomeItemFromCartToOrder(OrderRequestDTO  orderRequestDTO);



}
