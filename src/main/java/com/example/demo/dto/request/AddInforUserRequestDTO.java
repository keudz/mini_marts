package com.example.demo.dto.request;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
@Data
public class AddInforUserRequestDTO {
    private String email;

    private String realName;

    private BigInteger numberPhone;

    private String address;

    private String sex;

    private LocalDate birthday;

    private String image;

    public AddInforUserRequestDTO() {
    }

    public AddInforUserRequestDTO(String realName, String address, BigInteger numberPhone, String sex, LocalDate birthday) {
        this.realName = realName;
        this.address = address;
        this.numberPhone = numberPhone;
        this.sex = sex;
        this.birthday = birthday;
    }
}