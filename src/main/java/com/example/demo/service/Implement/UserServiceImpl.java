package com.example.demo.service.Implement;

import com.example.demo.dto.request.AddInforUserRequestDTO;
import com.example.demo.dto.request.AddProductToCartRequestDTO;
import com.example.demo.dto.request.DeleteItemFromCartRequestDTO;
import com.example.demo.dto.request.EmailRequest;
import com.example.demo.dto.request.OrderRequestDTO;
import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.request.OrderCartRequestDTO;
import com.example.demo.dto.request.CartItemDTO;
import com.example.demo.dto.response.AddProductInCartResponseDTO;
import com.example.demo.dto.response.OrderItemListResponceDTO;
import com.example.demo.dto.response.OrderResponceDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Cart_Iterm;
import com.example.demo.entity.Order_Iterm;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    private OrderItemRepository orderItemRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;



    @Override
    public UserCreateResponseDTO registerUser(UserCreateRequestDTO user){
        userValidateServiceImpl.ValidateCheckCreate(user);

        User userCreate = new User();
        userCreate.setFullName(user.getFullName());

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userCreate.setPassword(encodedPassword);

        userCreate.setEmail(user.getEmail());
        userCreate.setRole("user");
        userCreate.setStatus("active");
        userCreate.setIsDelete(false);

        // --- SỬA TẠI ĐÂY ---
        Cart cart = new Cart();
        cart.setUser(userCreate);   // QUAN TRỌNG: Thiết lập chiều ngược lại
        userCreate.setCart(cart);   // Thiết lập chiều xuôi
        // -------------------

        userRepository.save(userCreate); // Cascade sẽ lo phần còn lại

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
            if (cart_iterm.getProduct() == null) {
                continue; // Bỏ qua nếu không có product, tránh lỗi NullPointerException
            }
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
    public OrderResponceDTO useOrderSomeItemFromCartToOrder(OrderCartRequestDTO request) {
        // Kiểm tra đầu vào: Tránh trường hợp DTO gửi lên không có danh sách sản phẩm dẫn đến NullPointerException
        if (request.getProductNames() == null || request.getProductNames().isEmpty()) {
            throw new ApiException(400, "Danh sách tên sản phẩm (productNames) không được để trống.");
        }

        // Tìm User theo email
        User userRes = userRepository.selectUserByEmail(request.getEmail());
        if (userRes == null) {
            throw new ApiException(404, "User not found");
        }

        Cart cart = userRes.getCart();
        if (cart == null || cart.getCartItermList().isEmpty()) {
            throw new ApiException(400, "Cart is empty");
        }

        // Khởi tạo 1 đối tượng Orders duy nhất
        Orders order = new Orders();
        order.setUser(userRes);
        order.setSTATUS("PENDING");
        order.setDESCRIPTION("Order items directly from cart");
        order.setIsDelete(false);

        // Lưu trước để lấy ID (Manual Save phụ trợ hoặc để Cascade bám đúng FK)
        order = orderRepository.save(order);

        int totalAmount = 0;
        List<OrderItemListResponceDTO> orderListTemp = new ArrayList<>();
        List<Cart_Iterm> itemsToRemove = new ArrayList<>();

        // Duyệt qua request product names
        for (String productName : request.getProductNames()) {
            // Tìm CartItem tương ứng trong giỏ hàng
            Cart_Iterm matchedCartItem = null;
            for (Cart_Iterm ci : cart.getCartItermList()) {
                if (ci.getProduct() != null && ci.getProduct().getName().equalsIgnoreCase(productName)) {
                    matchedCartItem = ci;
                    break;
                }
            }

            if (matchedCartItem == null) {
                throw new ApiException(404, "Product not found in your cart: " + productName);
            }

            Product product = matchedCartItem.getProduct();
            int cartQuantity = matchedCartItem.getQUANTITY();

            // Kiểm tra tồn kho
            if (product.getStock() < cartQuantity) {
                throw new ApiException(400, "Product not enough in stock: " + product.getName());
            }

            // Trừ kho Product
            product.setStock(product.getStock() - cartQuantity);
            productRepository.save(product);

            // Tạo Order_Iterm
            Order_Iterm orderItem = new Order_Iterm();
            orderItem.setProduct(product);
            orderItem.setORIGINAL_PRICE(product.getOriginalPrice());
            orderItem.setPRICE(product.getPrice());
            orderItem.setQUANTITY(cartQuantity);

            // Thiết lập MQH với Orders
            orderItem.setOrder(order);
            
            // Xóa bớt rủi ro Silent Failure: Manual Save thêm nếu cần
            orderItem = orderItemRepository.save(orderItem);

            order.getOrderItermList().add(orderItem);

            // Cộng dồn
            totalAmount += product.getPrice() * cartQuantity;

            // Đánh dấu Cart_Iterm để xóa
            itemsToRemove.add(matchedCartItem);

            // Chuẩn bị Response DTO cho item
            OrderItemListResponceDTO itemResDTO = new OrderItemListResponceDTO();
            itemResDTO.setProductId(product.getID_PRODUCT());
            itemResDTO.setNameProduct(product.getName());
            itemResDTO.setQuantity(cartQuantity);
            itemResDTO.setPrice(product.getPrice());
            itemResDTO.setStock(product.getStock());
            itemResDTO.setCategory(product.getCategory());
            itemResDTO.setDescription(product.getDescription());
            itemResDTO.setImagelink(product.getImagelink());
            orderListTemp.add(itemResDTO);
        }

        // Lưu tổng tiền
        order.setTOTAL_AMOUNT(totalAmount);
        
        // Lưu orderRepository.save(orders) => (Cập nhật cập nhật tổng tiền Cascade liên kết luôn)
        orderRepository.save(order);

        // Xóa các Cart_Iterm đã được chuyển thành Order
        cart.getCartItermList().removeAll(itemsToRemove);
        cartItemRepository.deleteAll(itemsToRemove);
        cartRepository.save(cart);

        // Response
        OrderResponceDTO orderResponceDTO = new OrderResponceDTO();
        orderResponceDTO.setIdOrder(order.getID_ORDER());
        orderResponceDTO.setIdUser(userRes.getIdUser());
        orderResponceDTO.setTotal_amount(totalAmount);
        orderResponceDTO.setDes(order.getDESCRIPTION());
        orderResponceDTO.setStatus(order.getSTATUS());
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
    
    @Override
    @Transactional// Cực kỳ quan trọng để lưu được cả Order và Order_Item cùng lúc
    public OrderResponceDTO useOrderItem(OrderRequestDTO orderRequestDTO) {

        // 1. Kiểm tra User
        User useRes = userRepository.selectUserByEmail(orderRequestDTO.getEmail());
        if (useRes == null) {
            throw new ApiException(404, "User not found");
        }

        // 2. Kiểm tra Sản phẩm & Kho
        Product productRes = productRepository.findByName(orderRequestDTO.getProductName());
        if (productRes == null) {
            throw new ApiException(404, "Product not found");
        }
        if (productRes.getStock() < orderRequestDTO.getQuantity()) {
            throw new ApiException(400, "Product not enough in stock");
        }

        // 3. Trừ kho Product ngay sau khi kiểm tra
        productRes.setStock(productRes.getStock() - orderRequestDTO.getQuantity());
        productRepository.save(productRes);

        // 4. Khởi tạo Đơn hàng (Orders)
        Orders orders = new Orders();
        orders.setUser(useRes);
        orders.setDESCRIPTION("Order selected items");
        orders.setSTATUS("PENDING");
        orders.setIsDelete(false);
        orders.setTOTAL_AMOUNT(productRes.getPrice() * orderRequestDTO.getQuantity());

        // 5. LƯU ORDERS TRƯỚC ĐỂ LẤY ID (Manual Save Strategy)
        orders = orderRepository.save(orders);

        // 6. Khởi tạo Chi tiết đơn hàng (Order_Item)
        Order_Iterm orderItem = new Order_Iterm();
        orderItem.setProduct(productRes);
        orderItem.setORIGINAL_PRICE(productRes.getOriginalPrice());
        orderItem.setPRICE(productRes.getPrice());
        orderItem.setQUANTITY(orderRequestDTO.getQuantity());

        // 7. Thiết lập liên kết khóa ngoại với Order đã có ID
        orderItem.setOrder(orders);

        // 8. LƯU ORDER_ITEM THỦ CÔNG 
        orderItem = orderItemRepository.save(orderItem);

        // (Tùy chọn) Thêm vào list nếu cần sử dụng lại đối tượng đang trên Cache
        orders.getOrderItermList().add(orderItem);

        // 9. Chuyển đổi sang Response DTO và trả về
        OrderResponceDTO responseDTO = new OrderResponceDTO();
        responseDTO.setIdOrder(orders.getID_ORDER()); // Đảm bảo ID được ánh xạ đúng cách
        responseDTO.setIdUser(useRes.getIdUser());
        responseDTO.setDes(orders.getDESCRIPTION());
        responseDTO.setStatus(orders.getSTATUS());
        responseDTO.setTotal_amount(orders.getTOTAL_AMOUNT());

        // Tạo Item list cho Response
        OrderItemListResponceDTO itemResDTO = new OrderItemListResponceDTO();
        itemResDTO.setProductId(productRes.getID_PRODUCT());
        itemResDTO.setNameProduct(productRes.getName());
        itemResDTO.setQuantity(orderRequestDTO.getQuantity());
        itemResDTO.setPrice(productRes.getPrice());
        itemResDTO.setStock(productRes.getStock());
        itemResDTO.setCategory(productRes.getCategory());
        itemResDTO.setDescription(productRes.getDescription());
        itemResDTO.setImagelink(productRes.getImagelink());

        List<OrderItemListResponceDTO> itemResList = new ArrayList<>();
        itemResList.add(itemResDTO);
        responseDTO.setOrderItermList(itemResList);

        return responseDTO;
    }

    @Override
    public List<OrderResponceDTO> getAllOrderUser (EmailRequest emailRequest){
          User useRes = userRepository.selectUserByEmail(emailRequest.getEmail());
          if(useRes == null){
              throw new ApiException(404, "User not found");
          }
          List<OrderResponceDTO> orderResponceDTO = new ArrayList<>();
          List<Orders> orders = useRes.getOrder();
          for(Orders order:orders){
              List<Order_Iterm> orderItermList = order.getOrderItermList();
              List<OrderItemListResponceDTO> orderItemListResponceDTO = new ArrayList<>();
              OrderResponceDTO orderItem = new OrderResponceDTO();
              orderItem.setIdOrder(order.getID_ORDER());
              orderItem.setDes(order.getDESCRIPTION());
              for(Order_Iterm orderIterm :orderItermList){
                  OrderItemListResponceDTO itemResDTO = new OrderItemListResponceDTO();
                  itemResDTO.setProductId(orderIterm.getProduct().getID_PRODUCT());
                  itemResDTO.setNameProduct(orderIterm.getProduct().getName());
                  itemResDTO.setQuantity(orderIterm.getQUANTITY());
                  itemResDTO.setPrice(orderIterm.getProduct().getPrice());
                  itemResDTO.setImagelink(orderIterm.getProduct().getImagelink());
                  orderItemListResponceDTO.add(itemResDTO);
              }
              orderItem.setOrderItermList(orderItemListResponceDTO);
              orderResponceDTO.add(orderItem);
          }
   return orderResponceDTO;
    }
}