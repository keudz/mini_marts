package com.example.demo.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserCreateResponseDTO {
    private String email;
    private String Name;


    public UserCreateResponseDTO(String email, String Name) {
        this.email = email;
        this.Name = Name;;
    }

    public UserCreateResponseDTO() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
