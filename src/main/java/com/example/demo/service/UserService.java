package com.example.demo.service;

import com.example.demo.dto.request.*;
import com.example.demo.dto.response.AddProductInCartResponseDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;

import java.util.List;

public interface UserService {
    UserCreateResponseDTO login(UserLoginRequestDTO user);

    UserCreateResponseDTO registerUser (UserCreateRequestDTO user);

    UserCreateResponseDTO getUserById(int id);

    List<ProductResponseDTO> showallproduct();

    AddProductInCartResponseDTO addProductInCart(AddProductToCartRequestDTO addProductToCartRequestDTO);

    List<ProductResponseDTO> userCheckListProductInCart(EmailRequest user);

    String userDeleteProductInCart(DeleteItemFromCartRequestDTO delete);

    Object useOrderAllItemInCartToOrder(EmailRequest email);

    Object useOrderSomeItemFromCartToOrder(OrderRequestDTO  orderRequestDTO);

     void addInforUser(AddInforUserRequestDTO addInforUserRequestDTO);








}
