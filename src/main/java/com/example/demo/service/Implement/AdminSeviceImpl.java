package com.example.demo.service.Implement;

import com.example.demo.dto.request.Stringg;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Override

   public List<User>  showAllUser() {
      return userRepository.findAll();
  }

  @Override
    public UserResponDTO blockUser(Stringg name) {
      UserResponDTO userResponDTO = new UserResponDTO();
      int rowEffect  = userRepository.blockUserStatus(name.getName());
      if(rowEffect <= 0){
       throw new ApiException(404,"user not found");

      }
      User user =  userRepository.selectUserByName(name.getName());
      userResponDTO.setID_USER(user.getID_USER());
      userResponDTO.setEmail(user.getEmail());
     userResponDTO.setFullname(user.getFullname());
     userResponDTO.setRole(user.getRole());
     userResponDTO.setStatus(user.getStatus());

      return userResponDTO;
  }


  @Override
    public Product addProduct(Product product) {
      Product newProduct = new Product();
      newProduct.setName(product.getName());
      newProduct.setPrice(product.getPrice());
      newProduct.setCategory(product.getCategory());
      newProduct.setOriginal_price(product.getOriginal_price());
      newProduct.setStock(product.getStock());
      productRepository.save(newProduct);
      return newProduct;
      }


  @Override
  public Product updateProduct (int id,String Attribute , String information) {

   Optional<Product> Product = productRepository.findById(id);
   if(Product.isPresent()){
       Product newProduct = Product.get();// để lấy các phương thức ở trong newProduct thì cần phai khai báo thêm dòng này
    if(Attribute.equals("name")){
       newProduct.setName(information);
    }
    if(Attribute.equals("price")){
        newProduct.setPrice(Double.parseDouble(information));//Double.parseDouble:là hàm chuyền từ String sang Double
    }
    if(Attribute.equals("category")){
        newProduct.setCategory(information);
    }
    if(Attribute.equals("original_price")){
        newProduct.setOriginal_price(Double.parseDouble(information));
          }
    if(Attribute.equals("stock")){
        newProduct.setStock(Integer.parseInt(information));
    }
    productRepository.save(newProduct);
    return newProduct;
       }
     throw new ApiException(404,"product not found");
    }


    @Override
    public Product deleteProduct (int id) {
      Optional<Product> product = productRepository.findById(id);
      if(product.isPresent()){
          Product product1 = product.get();
          productRepository.deleteById(id);
          return product1;
      }
         return null;
    }

    @Override
    public List<ProductResponseDTO> showAllProduct() {
      List<Product> productOriginal = productRepository.findAll();
      List<ProductResponseDTO> ListProductRespon = new ArrayList<>();
      for(Product product : productOriginal){
          ProductResponseDTO productResponseDTO = new ProductResponseDTO();
          productResponseDTO.setNameProduct(product.getName());
          productResponseDTO.setPriceProduct(product.getPrice());
          productResponseDTO.setQuantity(product.getStock());
          productResponseDTO.setCategoryProduct(product.getCategory());
          ListProductRespon.add(productResponseDTO);
      }
      return ListProductRespon;
    }
}

