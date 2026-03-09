package com.example.demo.service.Implement;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Cart_Iterm;
import com.example.demo.entity.Product;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User; // ✅ đường dẫn đúng của class bạn

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
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public UserCreateResponseDTO createUser(UserCreateRequestDTO user) {
        User user1 = userRepository.selectUserByEmail(user.getEmail());
            if(user1!=null){
                throw new ApiException(400,"user already exists");
            }
        User userCreate = new User();
        userCreate.setFullname(user.getFullname());
        userCreate.setPassword(user.getPassword());
        userCreate.setEmail(user.getEmail());
        userCreate.setRole("Costumer");
        userCreate.setStatus("Active");
        userRepository.save(userCreate);
        UserCreateResponseDTO userRes = new UserCreateResponseDTO();
        userRes.setEmail(user.getEmail());
        userRes.setName(user.getFullname());
        return userRes;
    }


    @Override
    public Object login(UserLoginRequestDTO user) {
        int error = userValidateServiceImpl.ValidateCheckLogin(user);
        if (error == 1) {
            throw new ApiException(400,"Field is mandatory");
        }
        if(error == 2 || error == 3){
            throw new ApiException(400,"Password or email must be at least 8 character long");
        }
        return "Login Successful";
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
            productResDTO.setNameProduct(product.getName());
            productResDTO.setPriceProduct(product.getPrice());
            productResDTO.setQuantity(product.getStock());
            productResDTO.setCategoryProduct(product.getCategory());
            productRes.add(productResDTO);
        }
        return productRes;
    }

    @Override
    public Object addProductToCart(UserLoginRequestDTO user1, String nameProduct, int quantity) {
        int error =  userValidateServiceImpl.ValidateCheckLogin(user1);
        if (error == 1) {
            throw new  ApiException(400,"Field is mandatory");
        }

        User user = userRepository.selectUserByEmailAndPassWord(user1.getEmail(), user1.getPassword());
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
    public Object userCheckListProduct(UserLoginRequestDTO user) {

        User user1 = userRepository.selectUserByEmailAndPassWord(user.getEmail(), user.getPassword());
        if (user1 == null) {
            throw new ApiException(404,"user not found");
        }
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        Cart cart = user1.getCart();
        List<Cart_Iterm> cart_ItermList = cart.getCartItermList();
        for (Cart_Iterm cart_iterm : cart_ItermList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setNameProduct(cart_iterm.getProduct().getName());
            productResponseDTO.setDescriptionProduct(cart_iterm.getProduct().getDescription());
            productResponseDTO.setPriceProduct(cart_iterm.getProduct().getPrice());
            productResponseDTO.setCategoryProduct(cart_iterm.getProduct().getCategory());
            productResponseDTO.setQuantity(cart_iterm.getQUANTITY());
            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }

    @Override
    public Object userDeleteProduct(UserLoginRequestDTO user, String nameProduct) {
       int error = userValidateServiceImpl.ValidateCheckLogin(user);
        if (error == 1) {
            throw new  ApiException(400,"Field is mandatory");
        }
        User user1 = userRepository.selectUserByEmailAndPassWord(user.getEmail(), user.getPassword());
        if (user1 == null) {
           throw new ApiException(404,"user not found");
        }
        Cart cart = user1.getCart();
        if (cart == null || cart.getCartItermList().isEmpty()) {
           throw new ApiException(200,"cart is empty");
        }
        userRepository.deleteProductByName(nameProduct, cart.getID_CART());
        return "da xoa thanh cong san pham " + nameProduct + "ra khoi gio hang!!";
    }

}











