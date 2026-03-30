package com.example.demo.controller;

import com.example.demo.constant.UrlConstant;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.AdminService;
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


}

