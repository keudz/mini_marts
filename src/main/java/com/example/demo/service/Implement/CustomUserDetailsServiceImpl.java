package com.example.demo.service.Implement;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Tìm user trong DB bằng email.
        // Đảm bảo userRepository.findByEmail(email) trả về Optional<YourUserEntity>
        var userEntity = userRepository.selectUserByEmail(email);
                if(userEntity == null){
                    throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
                }


        // 2. Lấy quyền từ cột 'role' trong database (ví dụ: "admin" hoặc "user")
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole());

        // 3. Trả về đối tượng User của Spring Security (phải dùng full package để không bị đỏ)
        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(), // Dùng email làm username để định danh
                userEntity.getPassword(),
                Collections.singletonList(authority)
        );
    }
}