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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
public class AdminController {



    @Autowired
    private AdminService adminService;


    @PostMapping(UrlConstant.API_V1_BLOCK_USER)
    @PreAuthorize("hasAuthority('admin')")
    public void BlockUser(@RequestParam @Valid int id) {
        adminService.blockUser(id);
    }

    @PostMapping(UrlConstant.UNLOCK_USER)
    @PreAuthorize("hasAuthority('admin')")
    public void unLockUser(@RequestParam @Valid int id) {
        adminService.unlockUser(id);
    }


    @PostMapping(UrlConstant.API_V1_ADD_PRODUCT)
    @PreAuthorize("hasAuthority('admin')")
    public Object addProduct(@RequestBody ProductRequestDTO product) {
        return adminService.addProduct(product);
    }


    @PatchMapping(UrlConstant.API_V1_UPDATE_PRODUCT)
    @PreAuthorize("hasAuthority('admin')")
    public void updateProduct(@RequestBody ProductRequestDTO update) {
        adminService.updateProduct(update);
    }


    @DeleteMapping(UrlConstant.API_V1_DELETE_PRODUCT)
    @PreAuthorize("hasAuthority('admin')")
    public Object deleteProduct(@RequestBody int id) {
        return adminService.deleteProduct(id);
    }

    @Autowired
    private UserService getUser;
    @PostMapping(UrlConstant.API_V1_GET_USER)
    @PreAuthorize("hasAuthority('admin')")
    public UserCreateResponseDTO GetUser(@RequestBody int id) {
        return getUser.getUserById(id);
    }



    @PostMapping(UrlConstant.API_V1_SHOW_ALL_PRODUCT1)
    @PreAuthorize("hasAuthority('admin')")
    public List<ProductResponseDTO> showAllProduct() {
        return adminService.showAllProduct();
    }


    @PostMapping(UrlConstant.SHOW_ALL_USER)
    @PreAuthorize("hasAuthority('admin')")
    public List<UserResponDTO>  showAllUsers() {
        return adminService.showAllUser();
    }


}

