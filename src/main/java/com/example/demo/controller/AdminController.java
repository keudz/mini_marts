package com.example.demo.controller;

import com.example.demo.constant.UrlConstant;
import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
public class AdminController {



        @Autowired
    private AdminService blockUser;
    @PostMapping(UrlConstant.API_V1_BLOCK_USER)
  public void BlockUser(@RequestParam @Valid int id) {
        blockUser.blockUser(id);
    }
    @Autowired
    private AdminService unblockUser;
    @PostMapping(UrlConstant.UNLOCK_USER)
    public void unLockUser(@RequestParam @Valid int id) {
        unblockUser.unlockUser(id);
    }

    @Autowired
    private AdminService addProduct;
    @PostMapping(UrlConstant.API_V1_ADD_PRODUCT)
    public Object addProduct(@RequestBody ProductRequestDTO product) {
        return addProduct.addProduct(product);
    }

    @Autowired
    private AdminService updateProduct;
    @PatchMapping(UrlConstant.API_V1_UPDATE_PRODUCT)
    public void updateProduct(@RequestBody ProductRequestDTO update) {
              updateProduct.updateProduct(update);
    }

    @Autowired
    private AdminService deleteProduct;
    @DeleteMapping(UrlConstant.API_V1_DELETE_PRODUCT)
    public Object deleteProduct(@RequestBody int id) {
        return deleteProduct.deleteProduct(id);
    }

    @Autowired
    private UserService getUser;
    @PostMapping(UrlConstant.API_V1_GET_USER)
    public UserCreateResponseDTO GetUser(@RequestBody int id) {
        return getUser.getUserById(id);
    }


    @Autowired
    private AdminService showProductAdmin;
    @PostMapping(UrlConstant.API_V1_SHOW_ALL_PRODUCT1)
    public List<ProductResponseDTO> showAllProduct() {
        return showProductAdmin.showAllProduct();
    }

    @Autowired
    private AdminService showAllUsers;
    @PostMapping(UrlConstant.SHOW_ALL_USER)
    public List<UserResponDTO>  showAllUsers() {
        return showAllUsers.showAllUser();
    }


}

