package com.example.demo.service.Implement;

import com.example.demo.dto.request.*;
import com.example.demo.dto.response.*;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.*;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;



    @Override
    public UserCreateResponseDTO registerUser(UserCreateRequestDTO user){
        userValidateServiceImpl.ValidateCheckCreate(user);

        User userCreate = new User();
        userCreate.setFullName(user.getFullName());

        // MÃ HÓA MẬT KHẨU TẠI ĐÂY
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userCreate.setPassword(encodedPassword);

        userCreate.setEmail(user.getEmail());
        userCreate.setRole("user"); // Lưu ý: Nên để "user" viết thường cho khớp với hasAuthority("user")
        userCreate.setStatus("Active");
        userCreate.setIsDelete(false);

        userRepository.save(userCreate);
        Cart cart = new Cart();
        userCreate.setCart(cart);
        UserCreateResponseDTO userRes = new UserCreateResponseDTO();
        userRes.setEmail(user.getEmail());
        userRes.setName(user.getFullName());
        return userRes;
    }


    @Override
    public UserResponDTO login(UserLoginRequestDTO user) {
        return userValidateServiceImpl.ValidateCheckLogin(user);

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
        userRes.setName(user.getFullName());
        return userRes;
    }


    @Override
    public List<ProductResponseDTO> showallproduct() {
        List<Product> productsOriginal = productRepository.getProductByIsDelete();
        List<ProductResponseDTO> productRes = new ArrayList<>();
        for (Product product : productsOriginal) {
            ProductResponseDTO productResDTO = new ProductResponseDTO();
            productResDTO.setId(product.getID_PRODUCT());
            productResDTO.setNameProduct(product.getName());
            productResDTO.setPriceProduct(product.getPrice());
            productResDTO.setQuantityProduct(product.getStock());
            productResDTO.setCategoryProduct(product.getCategory());
            productResDTO.setDescriptionProduct(product.getDescription());
            productResDTO.setImageLink(product.getImagelink());
            productResDTO.setSubCategoryProduct(product.getSubCategory());
            productRes.add(productResDTO);
        }
        return productRes;
    }

    @Override
    public AddProductInCartResponseDTO addProductInCart(AddProductToCartRequestDTO addProductToCartRequestDTO) {
        User user = userRepository.selectUserByEmail(addProductToCartRequestDTO.getEmail());
        if (user == null) throw new ApiException(404, "User not found");

        Product product = productRepository.findByName(addProductToCartRequestDTO.getNameProduct());
        if (product == null) throw new ApiException(404, "Product not found");

        // KIỂM TRA TỒN KHO TRƯỚC KHI THÊM
        if (product.getStock() < addProductToCartRequestDTO.getQuantity()) {
            throw new ApiException(400, "Số lượng trong kho không đủ");
        }

        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setCartItermList(new ArrayList<>()); // Đảm bảo list không bị null
            user.setCart(cart);
        }

        Cart_Iterm existingItem = null;
        for (Cart_Iterm item : cart.getCartItermList()) {
            if (item.getProduct().getID_PRODUCT() == product.getID_PRODUCT()) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            existingItem.setQUANTITY(existingItem.getQUANTITY() + addProductToCartRequestDTO.getQuantity());
        } else {
            Cart_Iterm newItem = new Cart_Iterm();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQUANTITY(addProductToCartRequestDTO.getQuantity());
            cart.getCartItermList().add(newItem);
        }
        cartRepository.save(cart);
        AddProductInCartResponseDTO res = new AddProductInCartResponseDTO();
        res.setNameProduct(product.getName());
        res.setQuantity(addProductToCartRequestDTO.getQuantity());

        return res;
    }

    @Override
    public List<ProductResponseDTO> userCheckListProductInCart(EmailRequest email){
        User userRes = userRepository.selectUserByEmail(email.getEmail());
        if (userRes == null) {
            throw new ApiException(404,"user not found");
        }
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        Cart cart = userRes.getCart();
        List<Cart_Iterm> cart_ItermList = cart.getCartItermList();
        int i = 0;
        for (Cart_Iterm cart_iterm : cart_ItermList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(i++);
            productResponseDTO.setNameProduct(cart_iterm.getProduct().getName());
            productResponseDTO.setDescriptionProduct(cart_iterm.getProduct().getDescription());
            productResponseDTO.setPriceProduct(cart_iterm.getProduct().getPrice());
            productResponseDTO.setCategoryProduct(cart_iterm.getProduct().getCategory());
            productResponseDTO.setQuantityProduct(cart_iterm.getQUANTITY());
            productResponseDTO.setImageLink(cart_iterm.getProduct().getImagelink());
            productResponseDTO.setSubCategoryProduct(cart_iterm.getProduct().getSubCategory());
            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }

    @Override
    public String userDeleteProductInCart(DeleteItemFromCartRequestDTO delete){
        User userRes = userRepository.selectUserByEmail(delete.getEmail());
        if(userRes == null)
            throw new ApiException(404,"user not found");
        Cart cart = userRes.getCart();
        if (cart == null || cart.getCartItermList().isEmpty()){
            throw new ApiException(404,"cart is empty");
        }
        userRepository.deleteProductByName(delete.getNameProduct(), cart.getID_CART());
        return "da xoa thanh cong san pham " + delete.getNameProduct() + "ra khoi gio hang!!";
    }



    @Transactional
    @Override
    public OrderResponceDTO useOrderSomeItemFromCartToOrder(OrderRequestDTO orderRequestDTO){

        User userRes = userRepository.selectUserByEmail(orderRequestDTO.getEmail());
        if (userRes == null) {
            throw new ApiException(404, "User not found");
        }

        Cart cart = userRes.getCart();
        if (cart == null || cart.getCartItermList().isEmpty()) {
            throw new ApiException(400, "Cart is empty");
        }

        // 🔥 Lấy Product từ list tên
        List<Product> listProduct = new ArrayList<>();
        for (String orderRes : orderRequestDTO.getListProduct()){
            Product product = productRepository.findByName(orderRes);
            if (product == null) {
                throw new ApiException(404, "Product not found: " + orderRes);
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
            Product product = cartItem.getProduct();
            if(product.getStock() < cartItem.getQUANTITY()){
                throw new ApiException(400, "Product not enough");
            }
            product.setStock(product.getStock() - cartItem.getQUANTITY());
            productRepository.save(product);

            orderItem.setProduct(cartItem.getProduct());

            double price = cartItem.getProduct().getPrice();

            orderItem.setORIGINAL_PRICE(price);
            orderItem.setPRICE(price * cartItem.getQUANTITY());

            orderItem.setOrder(order);

            totalAmount += orderItem.getPRICE();

            orderItemList.add(orderItem);



        }



        order.setOrderItermList(orderItemList);
        order.setTOTAL_AMOUNT(totalAmount);
        orderRepository.save(order);
        cart.getCartItermList().removeAll(selectedItems);


        OrderResponceDTO orderResponceDTO = new OrderResponceDTO();
        orderResponceDTO.setTotal_amount(totalAmount);
        orderResponceDTO.setDes(order.getDESCRIPTION());
        orderResponceDTO.setStatus(order.getSTATUS());
        List<OrderItemListResponceDTO> orderListTemp  = new ArrayList<>();
        for(Cart_Iterm cartItem : selectedItems){
            OrderItemListResponceDTO orderListRes = new OrderItemListResponceDTO();
            orderListRes.setNameProduct(cartItem.getProduct().getName());
            orderListRes.setQuantity(cartItem.getQUANTITY());
            orderListRes.setPrice(cartItem.getProduct().getPrice());
            orderListTemp.add(orderListRes);
        }
        orderResponceDTO.setOrderItermList(orderListTemp);


        return orderResponceDTO;
    }

    @Override
    public void addInforUser(AddInforUserRequestDTO addInforUserRequestDTO){
        User useRes = userRepository.selectUserByEmail(addInforUserRequestDTO.getEmail());
        if(useRes == null){
            throw new ApiException(404, "User not found");
        }
        useRes.setRealName(addInforUserRequestDTO.getRealName());
        useRes.setNumberPhone(addInforUserRequestDTO.getNumberPhone());
        useRes.setAddress(addInforUserRequestDTO.getAddress());
        useRes.setImage(addInforUserRequestDTO.getImage());
        useRes.setSex(addInforUserRequestDTO.getSex());
        useRes.setBirthDay(addInforUserRequestDTO.getBirthDay());
        userRepository.save(useRes);
    }

}