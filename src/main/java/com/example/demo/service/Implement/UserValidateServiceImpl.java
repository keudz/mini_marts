package com.example.demo.service.Implement;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.service.UserValidateSevice;
import org.springframework.stereotype.Service;

@Service
public class UserValidateServiceImpl implements UserValidateSevice {

    @Override
    public int ValidateCheckLogin(UserLoginRequestDTO user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            return 1;
        }

       return 0;


    }

    @Override
    public int ValidateCheckCreate(UserCreateRequestDTO user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getFullname() == null || user.getFullname().isEmpty()||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            return 1;
        }

        if (user.getEmail().length() < 10) {
            return 2;
        }

        if (user.getPassword().length() < 8) {
            return 3;
        }

        return 0;
    }


}
