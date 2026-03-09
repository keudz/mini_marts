//@Override
//public String addProduct(String nameProduct, int idUser, int quantity) {
//    // Tìm sản phẩm theo tên
//    Product product = productRepository.findByName(nameProduct);
//    if (product == null) {
//        return "Không tìm thấy sản phẩm: " + nameProduct;
//    }
//
//    // Kiểm tra số lượng tồn kho
//    if (product.getStock() < quantity) {
//        return "Sản phẩm không đủ hàng tồn kho!";
//    }
//
//    // Tìm người dùng theo ID
//    Optional<User> userOptional = userRepository.findById(idUser);
//    if (!userOptional.isPresent()) {
//        return "Không tìm thấy người dùng!";
//    }
//
//    User user = userOptional.get();
//    Cart cart = user.getCart();
//
//    // Nếu người dùng chưa có giỏ hàng, tạo mới
//    if (cart == null) {
//        cart = new Cart();
//        cart.setUser(user);
//        cartRepository.save(cart);
//        user.setCart(cart);
//        userRepository.save(user);
//    }
//
//    // Tìm sản phẩm đã có trong giỏ hàng chưa
//    boolean itemExists = false;
//    for (Cart_Iterm item : cart.getItems()) {
//        if (item.getProduct().getId() == product.getId()) {
//            item.setQuantity(item.getQuantity() + quantity);
//            itemExists = true;
//            break;
//        }
//    }
//
//    // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
//    if (!itemExists) {
//        Cart_Iterm newItem = new Cart_Iterm();
//        newItem.setCart(cart);
//        newItem.setProduct(product);
//        newItem.setQuantity(quantity);
//        cart.getItems().add(newItem); // thêm vào danh sách item của giỏ
//    }
//
//    // Cập nhật lại số lượng tồn kho của sản phẩm
//    product.setStock(product.getStock() - quantity);
//    productRepository.save(product);
//
//    // Lưu lại giỏ hàng
//    cartRepository.save(cart);
//
//    return "Đã thêm sản phẩm '" + nameProduct + "' vào giỏ hàng!";
//}
