package com.example.demo.service.Implement;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.request.UpdateProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

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
        newProduct.setOriginal_price(product.getOriginalPrice());
        newProduct.setStock(product.getStock());
        newProduct.setDescription(product.getDescription());
        newProduct.setImagelink(product.getImagelink());
        newProduct.setSubCategory(product.getSubCategory());
        productRepository.save(newProduct);
        return newProduct;
    }


    @Override
    public Product updateProduct(UpdateProductRequestDTO update) {
        // 1. Tìm sản phẩm trong DB bằng ID từ DTO
        Optional<Product> productOptional = productRepository.findById(update.getId());

        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();

            // Lấy các giá trị từ DTO
            String attribute = update.getAttribute().toLowerCase();
            String information = update.getInformation();

            // 2. Kiểm tra Attribute nào cần update bằng if-else
            if (attribute.equals("name")) {
                existingProduct.setName(information);
            }
            else if (attribute.equals("price")) {
                existingProduct.setPrice(Double.parseDouble(information));
            }
            else if (attribute.equals("category")) {
                existingProduct.setCategory(information);
            }
            else if (attribute.equals("original_price") || attribute.equals("originalprice")) {
                existingProduct.setOriginal_price(Double.parseDouble(information));
            }
            else if (attribute.equals("stock")) {
                existingProduct.setStock(Integer.parseInt(information));
            }
            else if (attribute.equals("description")) {
                existingProduct.setDescription(information);
            }

            // 3. Lưu và trả về kết quả
            return productRepository.save(existingProduct);
        }

        // 4. Báo lỗi nếu không tìm thấy ID
        throw new ApiException(404, "Không tìm thấy sản phẩm có ID: " + update.getId());
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
            productResponseDTO.setId(product.getID_PRODUCT());
            productResponseDTO.setNameProduct(product.getName());
            productResponseDTO.setPriceProduct(product.getPrice());
            productResponseDTO.setQuantity(product.getStock());
            productResponseDTO.setDescriptionProduct(product.getDescription());
            productResponseDTO.setCategoryProduct(product.getCategory());
            productResponseDTO.setImageLink(product.getImagelink());
            productResponseDTO.setSubCategoryProduct(product.getSubCategory());
            ListProductRespon.add(productResponseDTO);
        }
        return ListProductRespon;
    }
}