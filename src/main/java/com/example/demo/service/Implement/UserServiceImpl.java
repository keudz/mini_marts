package com.example.demo.service.Implement;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.ExceptionResponceDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserValidateServiceImpl userValidateServiceImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;



    @Override
    public UserCreateResponseDTO registerUser(UserCreateRequestDTO user) {
        userValidateServiceImpl.ValidateCheckCreate(user);
        User userCreate = new User();
        userCreate.setFullname(user.getFullname());
        userCreate.setPassword(user.getPassword());
        userCreate.setEmail(user.getEmail());
        userCreate.setRole("Costumer");
        userCreate.setStatus("Active");
        userRepository.save(userCreate);
        Cart cart = new Cart();
        cart.setUser(userCreate);
        userCreate.setCart(cart);
        userRepository.save(userCreate);
        UserCreateResponseDTO userRes = new UserCreateResponseDTO();
        userRes.setEmail(user.getEmail());
        userRes.setName(user.getFullname());
        return userRes;
    }


    @Override
    public boolean login(UserLoginRequestDTO user) {
       userValidateServiceImpl.ValidateCheckLogin(user);
        return true;
}

    @Override
    public UserCreateResponseDTO getUserById(int id) {
        if( id < 0  ){
            throw new ApiException(400,"id is invalid");
        }
        User user = userRepository.selectUserById(id);
        if(user == null){
            throw new ApiException(404,"user not found");
        }
        UserCreateResponseDTO userRes = new UserCreateResponseDTO();
        userRes.setEmail(user.getEmail());
        userRes.setName(user.getFullname());
        return userRes;
    }


    @Override
    public List<ProductResponseDTO> showallproduct() {
        List<Product> productsOriginal = productRepository.findAll();
        List<ProductResponseDTO> productRes = new ArrayList<>();
        for (Product product : productsOriginal) {
            ProductResponseDTO productResDTO = new ProductResponseDTO();
            productResDTO.setId(product.getID_PRODUCT());
            productResDTO.setNameProduct(product.getName());
            productResDTO.setPriceProduct(product.getPrice());
            productResDTO.setQuantity(product.getStock());
            productResDTO.setCategoryProduct(product.getCategory());
            productResDTO.setDescriptionProduct(product.getDescription());
            productResDTO.setImageLink(product.getImagelink());
            productResDTO.setSubCategoryProduct(product.getSubCategory());
            productRes.add(productResDTO);
        }
        return productRes;
    }

    @Override
    public String addProductToCart(String email, String nameProduct, int quantity) {
//        int error =  userValidateServiceImpl.ValidateCheckLogin(user1);
//        if (error == 1) {
//            throw new  ApiException(400,"Field is mandatory");
//        }

        User user = userRepository.selectUserByEmail(email);
        if(user == null){
            throw new ApiException(400,"Password or email is invalid");
        }

        Product product = productRepository.findByName(nameProduct);
        if (product == null) {
            throw new ApiException(404,"product not found");
        }
        Cart cart = user.getCart();
        Cart_Iterm cart_Iterm = new Cart_Iterm();
        cart_Iterm.setCart(cart);
        cart_Iterm.setProduct(product);
        cart_Iterm.setQUANTITY(quantity);
        cart.getCartItermList().add(cart_Iterm);
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        cartRepository.save(cart);

        return "đã thêm sản phẩm " + nameProduct + "vào giỏ hàng!!";
    }

    @Override
    public List<ProductResponseDTO> userCheckListProductInCart(String email){
        User userRes = userRepository.selectUserByEmail(email);
        if (userRes == null) {
            throw new ApiException(404,"user not found");
        }
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        Cart cart = userRes.getCart();
        List<Cart_Iterm> cart_ItermList = cart.getCartItermList();
        for (Cart_Iterm cart_iterm : cart_ItermList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setNameProduct(cart_iterm.getProduct().getName());
            productResponseDTO.setDescriptionProduct(cart_iterm.getProduct().getDescription());
            productResponseDTO.setPriceProduct(cart_iterm.getProduct().getPrice());
            productResponseDTO.setCategoryProduct(cart_iterm.getProduct().getCategory());
            productResponseDTO.setQuantity(cart_iterm.getQUANTITY());
            productResponseDTO.setImageLink(cart_iterm.getProduct().getImagelink());
            productResponseDTO.setSubCategoryProduct(cart_iterm.getProduct().getSubCategory());
            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }

    @Override
    public String userDeleteProductInCart(String email , String nameProduct){
        User userRes = userRepository.selectUserByEmail(email);
        Cart cart = userRes.getCart();
        if (cart == null || cart.getCartItermList().isEmpty()){
           throw new ApiException(200,"cart is empty");
        }
        userRepository.deleteProductByName(nameProduct, cart.getID_CART());
        return "da xoa thanh cong san pham " + nameProduct + "ra khoi gio hang!!";
    }

    @Override
     public  Object useOrderAllItemInCart(String email){
        User useRes = userRepository.selectUserByEmail(email);
        Cart cart = useRes.getCart();
        if(cart == null || cart.getCartItermList().isEmpty()){
            throw new ApiException(200,"cart is empty");
        }
        List<Cart_Iterm> cart_ItermList = cart.getCartItermList();
        List<Orders> ordersList = useRes.getOrder();
        List<Order_Iterm>  orders_ItermList = ordersList.
        for (Cart_Iterm cart_Iterm : cart_ItermList) {

        }
    }

}











