package com.example.demo.service.Implement;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.*;
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
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderRepository orderRepository;



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
    public Object useOrderAllItemInCartToOrder(String email) {

        User userRes = userRepository.selectUserByEmail(email);
        if (userRes == null) {
            throw new ApiException(404, "User not found");
        }

        Cart cart = userRes.getCart();
        if (cart == null || cart.getCartItermList().isEmpty()) {
            throw new ApiException(400, "Cart is empty");
        }

        List<Cart_Iterm> cartItemList = cart.getCartItermList();

        // 1. Tạo order mới
        Orders order = new Orders();
        order.setUser(userRes);
        order.setSTATUS("PENDING");
        order.setDESCRIPTION("Order from cart");

        List<Order_Iterm> orderItemList = new ArrayList<>();

        int totalAmount = 0;

        // 2. Convert CartItem -> OrderItem
        for (Cart_Iterm cartItem : cartItemList) {

            Order_Iterm orderItem = new Order_Iterm();

            orderItem.setProduct(cartItem.getProduct());

            double price = cartItem.getProduct().getPrice(); // giả sử Product có getPrice()

            orderItem.setPRICE(price);
            orderItem.setPRICE(price * cartItem.getQUANTITY());

            // set quan hệ
            orderItem.setOrder(order);

            totalAmount += orderItem.getPRICE();

            orderItemList.add(orderItem);
        }


        order.setOrderItermList(orderItemList);


        order.setTATAL_AMOUNT(String.valueOf(totalAmount));

        // 5. save order (cascade sẽ save orderItem)
        orderRepository.save(order);

        // 6. clear cart
        cartItemRepository.deleteAll(cartItemList);

        return order;
    }


    @Override
    public Object useOrderSomeItemFromCartToOrder(String email, List<String> listProductName) {

        User userRes = userRepository.selectUserByEmail(email);
        if (userRes == null) {
            throw new ApiException(404, "User not found");
        }

        Cart cart = userRes.getCart();
        if (cart == null || cart.getCartItermList().isEmpty()) {
            throw new ApiException(400, "Cart is empty");
        }

        // 🔥 Lấy Product từ list tên
        List<Product> listProduct = new ArrayList<>();
        for (String name : listProductName) {
            Product product = productRepository.findByName(name); // tự viết hàm này
            if (product == null) {
                throw new ApiException(404, "Product not found: " + name);
            }
            listProduct.add(product);
        }

        List<Cart_Iterm> cartItemList = cart.getCartItermList();

        // Lọc những cartItem có product nằm trong listProduct
        List<Cart_Iterm> selectedItems = new ArrayList<>();

        for (Cart_Iterm cartItem : cartItemList) {
            for (Product p : listProduct) {
                if (cartItem.getProduct().getID_PRODUCT() == p.getID_PRODUCT()) {
                    selectedItems.add(cartItem);
                    break;
                }
            }
        }

        if (selectedItems.isEmpty()) {
            throw new ApiException(400, "No matching products in cart");
        }

        // 1. Tạo order
        Orders order = new Orders();
        order.setUser(userRes);
        order.setSTATUS("PENDING");
        order.setDESCRIPTION("Order selected items");

        List<Order_Iterm> orderItemList = new ArrayList<>();
        int totalAmount = 0;

        // 2. Convert sang OrderItem
        for (Cart_Iterm cartItem : selectedItems) {

            Order_Iterm orderItem = new Order_Iterm();

            orderItem.setProduct(cartItem.getProduct());

            double price = cartItem.getProduct().getPrice();

            orderItem.setORIGINAL_PRICE(price);
            orderItem.setPRICE(price * cartItem.getQUANTITY());

            orderItem.setOrder(order); // 🔥 QUAN TRỌNG

            totalAmount += orderItem.getPRICE();

            orderItemList.add(orderItem);
        }

        // 3. Gán list
        order.setOrderItermList(orderItemList);

        // 4. Tổng tiền
        order.setTATAL_AMOUNT(String.valueOf(totalAmount));

        // 5. Lưu
        orderRepository.save(order);

        // 6. Xóa những item đã order khỏi cart
        cartItemRepository.deleteAll(selectedItems);

        return order;
    }

}











