package com.example.demo.secutity;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    // Lưu ý: Key này phải dài ít nhất 64 ký tự
    private final String JWT_SECRET = "YourSecretKeyForJWTGenerationMustBeAtLeast64CharactersLongForHS512Algorithm";
    private final long JWT_EXPIRATION = 604800000L; // 7 ngày

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    // 1. Tạo JWT từ thông tin User
    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        // Lấy danh sách quyền (roles) từ userPrincipal và chuyển thành String
        // Ví dụ: "user" hoặc "user,admin"
        String authorities = userPrincipal.getAuthorities().stream()
                .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                .collect(java.util.stream.Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // Đây thường là Email nếu bạn cấu hình Login bằng Email
                .claim("role", authorities)             // <--- QUAN TRỌNG: Thêm cái này để hết lỗi 403
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // 2. Lấy username từ JWT
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 3. Kiểm tra Token có hợp lệ không
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}