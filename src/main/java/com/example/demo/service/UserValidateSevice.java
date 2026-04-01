package com.example.demo.service;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;

public interface UserValidateSevice {
   UserResponDTO ValidateCheckLogin(UserLoginRequestDTO user);

    void ValidateCheckCreate(UserCreateRequestDTO user);
}
