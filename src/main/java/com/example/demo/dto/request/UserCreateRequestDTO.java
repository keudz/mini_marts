package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Data
public class UserCreateRequestDTO {

    @NotBlank(message = "Email is notblank")//@NotBlank đảm bảo chỗi không chỉ chưa khoảng trắng;
    @Size(min = 6, max = 50,message = "Email is valid")
    @Email(message = "Email is valid")
    private String email;
    @NotBlank(message = "PasswSSSord is notblank")
    @Size(min = 6, max = 50,message = "password invalid")
    private String password;
    @NotBlank(message = "Username is notblank")
    @Size(min = 6, max = 50,message = "Username is invalid")
    private String fullName;


    public UserCreateRequestDTO(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;

}

}
