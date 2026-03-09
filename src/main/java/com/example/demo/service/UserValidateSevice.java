package com.example.demo.service;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;

public interface UserValidateSevice {
   int ValidateCheckLogin(UserLoginRequestDTO user);

    int ValidateCheckCreate(UserCreateRequestDTO user);
}
