package com.example.demo.controller;

import com.example.demo.constant.UrlConstant;
import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
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
    public void deleteProduct(@RequestParam int id) {
         adminService.deleteProduct(id);
    }

    @Autowired
    private UserService getUser;
    @PostMapping(UrlConstant.API_V1_GET_USER)
    @PreAuthorize("hasAuthority('admin')")
    public UserCreateResponseDTO GetUser(@RequestParam int id) {
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


    // 1. Lấy danh sách tất cả User
//    @PostMapping(UrlConstant.GET_ALL_USERS_FOR_ADMIN)
//    public List<User> getAllUser() {
//        return adminService.getAllUser();
//    }
    @PostMapping(UrlConstant.REDO_PRODUCT_FOR_ADMIN)
    public void redoProductForAdmin(@RequestParam @Valid int id) {
        adminService.redoProduct(id);
    }

    @PostMapping(UrlConstant.GET_ALL_ORDERS_FOR_ADMIN)
    public List<Orders> getAllOrders() {
        return adminService.getAllOrders();
    }

    @PostMapping(UrlConstant.SET_SHIPPING_FOR_ORDER)
    public void setShippingForOrder(@RequestParam int id) {
        adminService.setDeLiveringForOrder(id);
    }

    @PostMapping(UrlConstant.SET_COMPLETED_FOR_ORDER)
    public void setCompletedForOrder(@RequestParam int id) {
        adminService.setCompletedForOrder(id);
    }


}

