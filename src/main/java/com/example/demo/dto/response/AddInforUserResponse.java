package com.example.demo.dto.response;

import java.time.LocalDate;

public class AddInforUserResponse {

    private String realName;

    private String numberPhone;

    private String address;

    private String sex;

    private LocalDate birthday;

    public AddInforUserResponse() {
    }

    public AddInforUserResponse(String realName, String address, String numberPhone, String sex, LocalDate birthday) {
        this.realName = realName;
        this.address = address;
        this.numberPhone = numberPhone;
        this.sex = sex;
        this.birthday = birthday;
    }
}
