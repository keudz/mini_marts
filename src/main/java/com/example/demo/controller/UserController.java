package com.example.demo.controller;


import com.example.demo.constant.UrlConstant;
import com.example.demo.dto.request.*;
import com.example.demo.dto.response.*;
import com.example.demo.entity.Product;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated

public class UserController {
      @Autowired
      private UserService userSevice;
    @PostMapping(UrlConstant.API_V1_CREATE_USER)
    public Object CreateUser(@Valid @RequestBody UserCreateRequestDTO user) {
     return userSevice.registerUser(user);
    }

    @Autowired
    private UserService userLoginAcount;
    @PostMapping(UrlConstant.API_V1_LOGIN_USERS)
    public UserResponDTO LoginUser(@Valid @RequestBody UserLoginRequestDTO user) {
       return userLoginAcount.login(user);
    }



    @Autowired
    private UserService showProduct;
    @PostMapping(UrlConstant.API_V1_SHOW_ALL_PRODUCT)
    public List<ProductResponseDTO> GetAllProduct() {
        return showProduct.showallproduct();
    }


     @Autowired
    private UserService addPtoductToCart;
     @PostMapping (UrlConstant.ADD_PRODUCT_TO_CART)
     public AddProductInCartResponseDTO AddProductToCart(@RequestBody AddProductToCartRequestDTO addProductToCartRequestDTO) {
             return addPtoductToCart.addProductInCart(addProductToCartRequestDTO);
     }

     @Autowired
     private UserService userCheckListProduct;
     @PostMapping(UrlConstant.USER_CHECK_LIST_PRODUCT)
    public Object userCheckListProduct(@Valid @RequestBody EmailRequest emailRequest) {
             return  userCheckListProduct.userCheckListProductInCart(emailRequest);
     }

     @Autowired
     private UserService userDeleteProduct;
     @DeleteMapping(UrlConstant.USER_DELETE_PRODUCT_BY_NAME)
    public String DeleteProductById(@RequestBody DeleteItemFromCartRequestDTO deleteProduct) {
         return userDeleteProduct.userDeleteProductInCart(deleteProduct);
     }

     @Autowired
    private UserService userOrderAllItemInCart;
     @PostMapping(UrlConstant.USER_ORDER_ALL_ITEM_IN_CART)
     public  Object useOrderAllItemInCart(@RequestBody EmailRequest email){
         return userOrderAllItemInCart.useOrderAllItemInCartToOrder(email);
     }

     @Autowired
     private UserService useOrderSomeItemInCart;
    @PostMapping(UrlConstant.USER_ORDER_SOME_ITEM_IN_CART)
    public  Object useOrderAllItemInCart(@RequestBody OrderRequestDTO orderRequestDTO){
        return useOrderSomeItemInCart.useOrderSomeItemFromCartToOrder(orderRequestDTO);
    }

    @Autowired
    private UserService addInforUser;
    @PostMapping(UrlConstant.ADD_INFOR_USER)
    public  void addInforUser(@RequestBody AddInforUserRequestDTO addInforUserRequestDTO){
         addInforUser.addInforUser(addInforUserRequestDTO);
    }

}
