package com.example.demo.service.Implement;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserValidateSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidateServiceImpl implements UserValidateSevice {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserCreateResponseDTO ValidateCheckLogin(UserLoginRequestDTO user) {
        User userRes = userRepository.selectUserByEmail(user.getEmail());
        UserCreateResponseDTO userCreateResponseDTO = new UserCreateResponseDTO();
        if (userRes == null) {
            throw new ApiException(404, "Use not found !");
        }
        if (!userRes.getPassword().equals(user.getPassword())) {
            throw new ApiException(400, "Invalid email or password");
        }
        userCreateResponseDTO.setEmail(userRes.getEmail());
        userCreateResponseDTO.setName(userRes.getFullname());
        return userCreateResponseDTO;



    }

    @Override
    public void ValidateCheckCreate(UserCreateRequestDTO user) {
        User useResByEmail = userRepository.selectUserByEmail(user.getEmail());
        User useResByPassword = userRepository.selectUserByPassword(user.getPassword());
        if (useResByEmail != null) {
            throw new ApiException(400, "Account already exists");
        }
        if (useResByPassword != null) {
            throw new ApiException(400, "Password already exists");
        }

    }
}
