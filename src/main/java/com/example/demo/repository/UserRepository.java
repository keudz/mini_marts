package com.example.demo.repository;

import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
       //User là bảng còn Integer là kiểu dữ liệu của id
       //dùng nativeQuery
       //các câu lệnh khác như select hay delete đều giống như dùng JPQL
       @Query(value = "SELECT u.* From users u where u.ID_USER = :id",nativeQuery = true)
       User selectUserById(@Param("id") int id );

       //dùng JPQL
       User findByFullname(String name);
       @Query("SELECT user From User user where user.email = :email AND user.password = :password ")
       //User là tên của class chứ k phải tên của table bảng users
       User selectUserByEmailAndPassWord(@Param("email") String email,@Param("password") String password);
       //dùng JPQL
       @Query("SELECT user From User user where user.email = :email")
       User selectUserByEmail(@Param("email") String email);
       //dùng JPQL
       @Transactional//dùng khi delete hay update,insest
       @Modifying//annocation này để đánh dấu đấy không phải thao tác select
       @Query("Delete FROM Cart_Iterm p Where p.product.name = :name AND p.cart.ID_CART =:id")
       int deleteProductByName(@Param ("name") String name,@Param("id") int id);
       //kiểu trả về là kiểu int là số row được xoas trong bảng Cart_iterm

       @Transactional
       @Modifying
       @Query("Update User u set u.status = 'block' Where u.fullname = :name")
       int blockUserStatus(@Param("name") String name);

       @Query("SELECT user From User user where user.fullname = :name")
       User selectUserByName(@Param("name") String name);






}
