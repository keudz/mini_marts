package com.example.demo.service.Implement;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.OrderItemListResponceDTO;
import com.example.demo.dto.response.OrderResponceDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Order_Iterm;
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
    private ProductRepository productRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Override

    public List<UserResponDTO> showAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponDTO> userResponDTOList = new ArrayList<>();
        for (User user : userList) {
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
    public Product addProduct(ProductRequestDTO product) {
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

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            if (update.getName() != null) {
                product.setName(update.getName());
            }
            if (update.getCategory() != null) {
                product.setCategory(update.getCategory());
            }
            if (update.getDescription() != null && !update.getDescription().isEmpty()) {
                product.setDescription(update.getDescription());
            }
            if (update.getImagelink() != null) {
                product.setImagelink(update.getImagelink());
            }
            if (update.getSubCategory() != null) {
                product.setSubCategory(update.getSubCategory());
            }
            if (update.getOriginalPrice() != null) {
                product.setOriginalPrice(update.getOriginalPrice());
            }
            if (update.getPrice() != null) {
                product.setPrice(update.getPrice());
            }
            if (update.getStock() != null) {
                product.setStock(update.getStock());
            }
            productRepository.save(product);
        } else {
            throw new ApiException(404, "product  not found");
        }

    }

    @Override
    public void deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productTemp = product.get();
            productTemp.setIsDelete(true);
            productRepository.save(productTemp);
        } else {
            throw new ApiException(404, "product not found");
        }
    }

    @Override
    public List<ProductResponseDTO> showAllProduct() {
        List<Product> productOriginal = productRepository.getProductByIsDelete();
        List<ProductResponseDTO> ListProductRespon = new ArrayList<>();
        for (Product product : productOriginal) {
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
        if (product.isPresent()) {
            Product productTemp = product.get();
            productTemp.setIsDelete(false);
            productRepository.save(productTemp);
        } else {
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
        List<OrderResponceDTO> responseList = new ArrayList<>();

        for (Orders order : ordersList) {
            OrderResponceDTO dto = new OrderResponceDTO();
            dto.setIdOrder(order.getID_ORDER());
            dto.setDes(order.getDESCRIPTION());
            dto.setStatus(order.getSTATUS());
            dto.setTotal_amount(order.getTOTAL_AMOUNT());

            // Xử lý thông tin User an toàn, tránh NullPointerException
            if (order.getUser() != null) {
                dto.setIdUser(order.getUser().getIdUser());
                dto.setUserEmail(order.getUser().getEmail());
                dto.setUserPhone(order.getUser().getNumberPhone());
            }

            // Xử lý danh sách Order Items
            List<OrderItemListResponceDTO> itemDTOs = new ArrayList<>();
            if (order.getOrderItermList() != null) {
                for (Order_Iterm item : order.getOrderItermList()) {
                    itemDTOs.add(mapToItemDTO(item, order));
                }
            }
            dto.setOrderItermList(itemDTOs);

            responseList.add(dto);
        }
        return responseList;
    }

    // Hàm Helper tách riêng để map dữ liệu và xử lý logic tính toán Quantity
    private OrderItemListResponceDTO mapToItemDTO(Order_Iterm orderItem, Orders order) {
        OrderItemListResponceDTO itemDTO = new OrderItemListResponceDTO();

        if (orderItem.getProduct() != null) {
            Product product = orderItem.getProduct();

            itemDTO.setProductId(product.getID_PRODUCT());
            itemDTO.setNameProduct(product.getName());
            itemDTO.setPrice(product.getPrice());
            itemDTO.setStock(product.getStock());
            itemDTO.setCategory(product.getCategory());
            itemDTO.setDescription(product.getDescription());
            itemDTO.setImagelink(product.getImagelink());

            // Logic tự động khôi phục số lượng (Quantity Recovery) nếu bị bằng 0
            int quantity = orderItem.getQUANTITY();
            if (quantity == 0) {
                if (orderItem.getORIGINAL_PRICE() > 0) {
                    quantity = (int) Math.round(orderItem.getPRICE() / orderItem.getORIGINAL_PRICE());
                } else if (product.getPrice() > 0 && order.getTOTAL_AMOUNT() > 0) {
                    quantity = (int) Math.round(order.getTOTAL_AMOUNT() / product.getPrice());
                }
            }
            itemDTO.setQuantity(quantity);
        }

        return itemDTO;
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