package com.example.demo.controller;


import com.example.demo.constant.UrlConstant;
import com.example.demo.dto.request.EmailRequest;
import com.example.demo.dto.request.OrderRequestDTO;
import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.AddProductInCartResponseDTO;
import com.example.demo.dto.response.OrderResponceDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
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
    public UserCreateResponseDTO LoginUser(@Valid @RequestBody UserLoginRequestDTO user) {
       return userLoginAcount.login(user);
    }


    @Autowired
    private UserService getUser;
    @PostMapping(UrlConstant.API_V1_GET_USER)
    public UserCreateResponseDTO GetUser( @RequestBody int id) {
       return getUser.getUserById(id);
    }

    @Autowired
    private UserService showProduct;
    @PostMapping(UrlConstant.API_V1_SHOW_ALL_PRODUCT)
    public List<ProductResponseDTO> GetAllProduct() {
        return showProduct.showallproduct();
    }

//    @Autowired
//    private AdminService blockUser;
//    @PostMapping(UrlConstant.API_V1_BLOCK_USER)
//  public Object BlockUser(@Valid @RequestBody String name, BindingResult result) {
//        if(result.hasErrors()) {
//            return result.getAllErrors().get(0).getDefaultMessage();
//        }
//            return blockUser.blockUser(name);
//    }

    @Autowired
    private AdminService addProduct;
    @PostMapping(UrlConstant.API_V1_ADD_PRODUCT)
    public Object AddProduct(@RequestBody Product product) {
        return addProduct.addProduct(product);
    }

    @Autowired
    private AdminService updateProduct;
    @PatchMapping(UrlConstant.API_V1_UPDATE_PRODUCT)
    public Object AddProduct(@RequestBody int id, @RequestBody String Attribute,@RequestBody String Information) {
           return updateProduct.updateProduct(id, Attribute, Information);
    }

    @Autowired
    private AdminService deleteProduct;
    @DeleteMapping(UrlConstant.API_V1_DELETE_PRODUCT)
    public Object DeleteProduct(@RequestBody int id) {
        return deleteProduct.deleteProduct(id);
    }

     @Autowired
     private AdminService showProductAdmin;
     @PostMapping(UrlConstant.API_V1_SHOW_ALL_PRODUCT1)
     public List<ProductResponseDTO> showAllProduct() {
         return showProductAdmin.showAllProduct();
     }

     @Autowired
    private UserService addPtoductToCart;
     @PostMapping (UrlConstant.ADD_PRODUCT_TO_CART)
     public AddProductInCartResponseDTO AddProductToCart(@RequestBody String email, @RequestBody String nameProduct, @RequestBody int quantity) {
             return addPtoductToCart.addProductInCart(email,nameProduct,quantity);
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
    public ResponseEntity<String> DeleteProductById(@RequestBody String email,@RequestBody String nameProduct) {
         return ResponseEntity.ok(userDeleteProduct.userDeleteProductInCart(email,nameProduct));
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
}
