package com.example.demo.service.Implement;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.OrderResponceDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class AdminSeviceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository  productRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Override

    public List<UserResponDTO>  showAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponDTO> userResponDTOList = new ArrayList<>();
        for(User user:userList){
            userResponDTOList.add(userMapper.userToUserResponDTO(user));
        }
        return userResponDTOList;
    }

    @Override
    public void blockUser(int id) {
        userRepository.blockUser(id);
    }
    @Override
    public void unlockUser(int id) {
        userRepository.activeUser(id);
    }


    @Override
    public Product addProduct(ProductRequestDTO  product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setCategory(product.getCategory());
        newProduct.setOriginalPrice(product.getOriginalPrice());
        newProduct.setStock(product.getStock());
        newProduct.setDescription(product.getDescription());
        newProduct.setImagelink(product.getImagelink());
        newProduct.setSubCategory(product.getSubCategory());
        productRepository.save(newProduct);
        return newProduct;
    }


    @Override
    public void updateProduct(ProductRequestDTO update) {
     Optional<Product> productOptional = productRepository.findById(update.getId());

     if(productOptional.isPresent()){
         Product product = productOptional.get();
         if(update.getName()!=null){
             product.setName(update.getName());
         }
         if(update.getCategory()!=null){
             product.setCategory(update.getCategory());
         }
         if(update.getDescription() != null && !update.getDescription().isEmpty()){
             product.setDescription(update.getDescription());
         }
         if(update.getImagelink()!=null){
             product.setImagelink(update.getImagelink());
         }
         if(update.getSubCategory()!=null){
             product.setSubCategory(update.getSubCategory());
         }
         if(update.getOriginalPrice() != null){
             product.setOriginalPrice(update.getOriginalPrice());
         }
         if(update.getPrice() != null){
             product.setPrice(update.getPrice());
         }
         if(update.getStock() != null){
             product.setStock(update.getStock());
         }
         productRepository.save(product);
     }
     else{
         throw new ApiException(404, "product  not found");
     }

    }


    @Override
    public void deleteProduct (int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product productTemp = product.get();
            productTemp.setIsDelete(true);
            productRepository.save(productTemp);
        }
        else{
            throw new ApiException(404, "product not found");
        }
    }

    @Override
    public List<ProductResponseDTO> showAllProduct() {
        List<Product> productOriginal = productRepository.getProductByIsDelete();
        List<ProductResponseDTO> ListProductRespon = new ArrayList<>();
        for(Product product : productOriginal){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(product.getID_PRODUCT());
            productResponseDTO.setNameProduct(product.getName());
            productResponseDTO.setPriceProduct(product.getPrice());
            productResponseDTO.setQuantityProduct(product.getStock());
            productResponseDTO.setDescriptionProduct(product.getDescription());
            productResponseDTO.setCategoryProduct(product.getCategory());
            productResponseDTO.setImageLink(product.getImagelink());
            productResponseDTO.setSubCategoryProduct(product.getSubCategory());
            ListProductRespon.add(productResponseDTO);
        }
        return ListProductRespon;
    }

    @Override
    public void redoProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product productTemp = product.get();
            productTemp.setIsDelete(false);
            productRepository.save(productTemp);
        }
        else {
            throw new ApiException(404, "product not found");
        }
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<OrderResponceDTO> getAllOrders() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderResponceDTO> orderResponceDTOList = new ArrayList<>();
        for(Orders order : ordersList){
            OrderResponceDTO orderResponceDTO = new OrderResponceDTO();
            orderResponceDTO.setIdOrder(order.getID_ORDER());
            orderResponceDTO.setDes(order.getDESCRIPTION());
            orderResponceDTO.setStatus(order.getSTATUS());
            orderResponceDTO.setTotal_amount(order.getTOTAL_AMOUNT());
            orderResponceDTO.setIdUser(order.getIdUser());
            orderResponceDTOList.add(orderResponceDTO);
        }
        return orderResponceDTOList;
    }

    @Override
    public void setDeLiveringForOrder(int id) {
       Orders orders = orderRepository.findByIdOrder(id);
       orders.setSTATUS("DELIVERING");
       orderRepository.save(orders);
    }

    @Override
    public void setCompletedForOrder(int id) {
        Orders orders = orderRepository.findByIdOrder(id);
        orders.setSTATUS("COMPLETED");
        orderRepository.save(orders);
    }
}