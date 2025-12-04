package com.khang.goldenage.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
  @Value("${jwt.secret}")
  private String jwtSecret;
  @Value("${jwt.expiration}")
  private long jwtExpirationMs; // 1 ngày

  public String generateToken(String email, String role, Long userId) {
    return Jwts.builder()
      .setSubject(email)
      .claim("userId", userId) // Thêm userId vào payload
      .claim("role", role)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
      .signWith(SignatureAlgorithm.HS256, jwtSecret)
      .compact();
  }

  // xac thuc token va lay thong tin tu token
  public Claims getClaimsFromToken(String token) {
    return Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)  // trả v cac claim (thanh phan cua token)
      .getBody();
  }

  // Kiểm tra token còn hiệu lực không
  public boolean validateToken(String token) {
    try {
      Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

}
