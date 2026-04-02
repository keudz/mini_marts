package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name =  "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER") // Chỉ định đúng tên cột trong SQL
    private Integer idUser;

    private String email;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    private String status;
    private String role;
    @Column(name = "real_name")
    private String realName;

    private String address;

    @Column(name = "number_phone")
    private BigInteger numberPhone;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    private String sex;
    private String image;

    @Column(name = "is_delete")
    private Boolean isDelete;




    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //mappedby:chỉ định rằng entity user là entity thuộc sở hữu mối quán hệ (entity không chưa khoá ngoại)
    //cascade = CascadeType.ALL là khi entity chính bị chỉnh sửathì thì các entity còn lại cũng sẽ bị  theo
    //fetch = FetchType.EAGER là khi thong tin của User bi lấy ra thì thông tin của Cart cũng se bị lấy ra
    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> order;

   public User (){};



}
