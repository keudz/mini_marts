package com.example.demo.service.Implement;

import com.example.demo.dto.request.UserCreateRequestDTO;
import com.example.demo.dto.request.UserLoginRequestDTO;
import com.example.demo.dto.response.UserCreateResponseDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.secutity.JwtTokenProvider;
import com.example.demo.service.UserValidateSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserValidateServiceImpl implements UserValidateSevice {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public UserResponDTO ValidateCheckLogin(UserLoginRequestDTO userRequest) {
        // 1. Dùng AuthenticationManager để xác thực (Tự động check Email & Password BCrypt)
        // Nó sẽ tự gọi CustomUserDetailsServiceImpl mà bạn đã viết trước đó
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getEmail(),
                            userRequest.getPassword()
                    )
            );

            // 2. Nếu đăng nhập thành công, tạo Token
            String jwt = tokenProvider.generateToken(authentication);

            // 3. Lấy thông tin User từ DB để trả về
            User userRes = userRepository.selectUserByEmail(userRequest.getEmail());

            // 4. Map sang DTO và đính kèm Token
            UserResponDTO response = userMapper.userToUserResponDTO(userRes);
            response.setToken(jwt); // Đảm bảo bạn đã thêm field 'token' vào UserResponDTO

            return response;

        } catch (BadCredentialsException e) {
            // Thay thế cho đoạn check password thủ công của bạn
            throw new ApiException(400, "Email hoặc mật khẩu không chính xác!");
        }
    }


    @Override
    public void ValidateCheckCreate(UserCreateRequestDTO user) {
        User useResByEmail = userRepository.selectUserByEmail(user.getEmail());
        if (useResByEmail != null) {
            throw new ApiException(400, "Email đã được đăng ký bởi tài khoản khác!");
        }
        // BỎ đoạn check password cũ đi
    }
}
