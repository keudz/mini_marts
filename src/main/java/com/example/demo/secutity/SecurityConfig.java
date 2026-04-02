package com.example.demo.secutity;

import com.example.demo.constant.UrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    // 1. CHỖ SỬA SỐ 1: Khai báo Filter của bạn vào đây để Spring tiêm (inject) vào
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. CHỖ SỬA SỐ 2: Tắt Session mặc định của Spring (Vì mình dùng JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // 1. NHÓM CÔNG KHAI (Public) - Ai cũng vào được
                        .requestMatchers(
                                UrlConstant.API_V1_CREATE_USER,
                                UrlConstant.API_V1_LOGIN_USERS,
                                UrlConstant.API_V1_SHOW_ALL_PRODUCT
                        ).permitAll()

                        // 2. NHÓM CHỈ ADMIN (Admin Only)
                        .requestMatchers(
                                UrlConstant.API_V1_BLOCK_USER,
                                UrlConstant.API_V1_DELETE_PRODUCT,
                                UrlConstant.API_V1_UPDATE_PRODUCT,
                                UrlConstant.API_V1_SHOW_ALL_PRODUCT1,
                                UrlConstant.UNLOCK_USER,
                                UrlConstant.SHOW_ALL_USER,
                                UrlConstant.REDO_PRODUCT_FOR_ADMIN
                        ).hasAuthority("admin")

                        // 3. NHÓM CHỨC NĂNG CHO USER (Admin cũng có quyền vào)
                        .requestMatchers(
                                UrlConstant.ADD_PRODUCT_TO_CART,
                                UrlConstant.USER_CHECK_LIST_PRODUCT,
                                UrlConstant.USER_DELETE_PRODUCT_BY_NAME,
                                UrlConstant.USER_ORDER_ALL_ITEM_IN_CART,
                                UrlConstant.USER_ORDER_SOME_ITEM_IN_CART,
                                UrlConstant.ADD_INFOR_USER,
                                UrlConstant.API_V1_GET_USER
                        ).hasAnyAuthority("user", "admin")

                        // 4. CÒN LẠI PHẢI ĐĂNG NHẬP
                        .anyRequest().authenticated()
                )

                // 3. CHỖ SỬA SỐ 3: Bắt Spring phải chạy qua Filter đọc JWT của bạn TRƯỚC KHI chốt quyền
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(403);
                            response.getWriter().write("{\"status\": 403, \"message\": \"Bạn không có quyền truy cập!\"}");
                        })
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("http://localhost:3000", "http://localhost:5173",
                "http://localhost:5174", "http://192.168.31.57:[*]"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}