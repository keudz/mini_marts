package com.example.demo.service;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;

public interface UserValidateSevice {
   void ValidateCheckLogin(UserLoginRequestDTO user);

    void ValidateCheckCreate(UserCreateRequestDTO user);
}
