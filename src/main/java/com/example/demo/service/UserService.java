package com.example.demo.service;

import com.example.demo.dto.request.*;
import com.example.demo.dto.response.*;

import java.util.List;

public interface UserService {
    UserResponDTO login(UserLoginRequestDTO user);

    UserCreateResponseDTO registerUser (UserCreateRequestDTO user);

    UserCreateResponseDTO getUserById(int id);

    List<ProductResponseDTO> showallproduct();

    AddProductInCartResponseDTO addProductInCart(AddProductToCartRequestDTO addProductToCartRequestDTO);

    List<ProductResponseDTO> userCheckListProductInCart(EmailRequest user);

    String userDeleteProductInCart(DeleteItemFromCartRequestDTO delete);

   // void undoProductInCart(ProductResponseDTO product);

    OrderResponceDTO useOrderSomeItemFromCartToOrder(OrderRequestDTO  orderRequestDTO);

     void addInforUser(AddInforUserRequestDTO addInforUserRequestDTO);

     OrderResponceDTO useOrderItem(OrderRequestDTO  orderRequestDTO);








}
