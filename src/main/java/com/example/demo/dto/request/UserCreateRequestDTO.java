package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateRequestDTO {
    @NotBlank(message = "Email is invalid")//@NotBlank đảm bảo chỗi không chỉ chưa khoảng trắng;
    @Size(min = 6, max = 50,message = "The email too short")
    @Email(message = "EMAIL IS INVALID!!")
    private String email;
    @NotBlank(message = "Password is invalid")
    @Size(min = 6, max = 50,message = "")
    private String password;
    @NotBlank(message = "Username is invalid")
    @Size(min = 6, max = 50,message = "Username is invalid")
    private String fullname;


    public UserCreateRequestDTO(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;

}
    public UserCreateRequestDTO() {}

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


}
