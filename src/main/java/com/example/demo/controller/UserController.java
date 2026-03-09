package com.example.demo.controller;


import com.example.demo.constant.UrlConstant;
import com.example.demo.dto.request.Stringg;
import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
     return userSevice.createUser(user);
    }

    @Autowired
    private UserService userLoginAcount;
    @PostMapping(UrlConstant.API_V1_LOGIN_USERS)
    public Object LoginUser(@Valid @RequestBody UserLoginRequestDTO user) {
       return userLoginAcount.login(user);
    }


    @Autowired
    private UserService getUser;
    @GetMapping(UrlConstant.API_V1_GET_USER)
    public UserCreateResponseDTO GetUser( @RequestParam int id) {
       return getUser.getUserById(id);
    }

    @Autowired
    private UserService showProduct;
    @GetMapping(UrlConstant.API_V1_SHOW_ALL_PRODUCT)
    public List<ProductResponseDTO> GetAllProduct() {
        return showProduct.showallproduct();
    }

    @Autowired
    private AdminService blockUser;
    @PostMapping(UrlConstant.API_V1_BLOCK_USER)
  public Object BlockUser(@Valid @RequestBody Stringg name, BindingResult result) {
        if(result.hasErrors()) {
            return result.getAllErrors().get(0).getDefaultMessage();
        }
            return blockUser.blockUser(name);
    }

    @Autowired
    private AdminService addProduct;
    @PostMapping(UrlConstant.API_V1_ADD_PRODUCT)
    public Object AddProduct(@RequestBody Product product) {
        return addProduct.addProduct(product);
    }

    @Autowired
    private AdminService updateProduct;
    @PatchMapping(UrlConstant.API_V1_UPDATE_PRODUCT)
    public Object AddProduct(@RequestParam int id, @RequestParam String Attribute,@RequestParam String Information) {
           return updateProduct.updateProduct(id, Attribute, Information);
    }

    @Autowired
    private AdminService deleteProduct;
    @DeleteMapping(UrlConstant.API_V1_DELETE_PRODUCT)
    public Object DeleteProduct(@RequestParam int id) {
        return deleteProduct.deleteProduct(id);
    }

     @Autowired
     private AdminService showProductAdmin;
     @GetMapping(UrlConstant.API_V1_SHOW_ALL_PRODUCT1)
     public List<ProductResponseDTO> showAllProduct() {
         return showProductAdmin.showAllProduct();
     }

     @Autowired
    private UserService addPtoductToCart;
     @GetMapping(UrlConstant.ADD_PRODUCT_TO_CART)
     public Object AddProductToCart(@Valid @RequestParam UserLoginRequestDTO user,@RequestParam String nameProduct,@RequestParam int quantity) {
             return addPtoductToCart.addProductToCart(user,nameProduct,quantity);
     }

     @Autowired
     private UserService userCheckListProduct;
     @GetMapping(UrlConstant.USER_CHECK_LIST_PRODUCT)
    public Object userCheckListProduct(@Valid @RequestBody  UserLoginRequestDTO user) {
             return  userCheckListProduct.userCheckListProduct(user);
     }

     @Autowired
     private UserService userDeleteProduct;
     @DeleteMapping(UrlConstant.USER_DELETE_PRODUCT_BY_NAME)
    public ResponseEntity<Object> DeleteProductById(@RequestParam UserLoginRequestDTO user, String nameProduct) {
         return ResponseEntity.ok(userDeleteProduct.userDeleteProduct(user,nameProduct));
     }
}
