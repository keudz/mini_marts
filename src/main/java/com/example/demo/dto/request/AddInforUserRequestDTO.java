package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInforUserRequestDTO {
    private String email;

    private String realName;

    private String numberPhone;

    private String address;

    private String sex;

    private LocalDate birthDay;

    private String image;



}