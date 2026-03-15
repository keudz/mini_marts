package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequestDTO {
    @NotBlank(message = "Email field cannot be left blank")
    private String email;
    @NotBlank(message = "Password field cannot be left blank")
    private String password;

    public UserLoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public UserLoginRequestDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
