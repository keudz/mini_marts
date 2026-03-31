package com.example.demo.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponDTO {
    private Integer idUser;

    private String email;

    private String password;

    private String fullName;

    private String status;

    private String role;

    private String realName;

    private String address;

    private BigInteger numberPhone;

    private LocalDate birthDay;

    private String sex;

    private String image;
}
