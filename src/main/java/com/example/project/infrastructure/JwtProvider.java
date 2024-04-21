package com.example.project.infrastructure;

import com.example.project.domain.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private final String secretKey;
  private static final String ISSUER = "E-COMMERCE";
  private static final String ACCESS_TOKEN_TYPE = "AT";
  private static final String REFRESH_TOKEN_TYPE = "RT";
  private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 2; //2시간
  private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 14; //2주

  public JwtProvider(@Value("${jwt.secret}") String secretKey) {
    this.secretKey = secretKey;
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public String createAccessToken(User user) {
    Date now = new Date();
    return Jwts.builder()
               .setSubject(user.getUserId().toString())
               .setHeaderParam("typ", ACCESS_TOKEN_TYPE)
               .setIssuer(ISSUER)
               .setIssuedAt(now)
               .claim("role", user.getUserRole().toString())
               .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME))
               .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
  }

  public String createRefreshToken(User user) {
    Date now = new Date();
    return Jwts.builder()
               .setSubject(user.getUserId().toString())
               .setHeaderParam("typ", REFRESH_TOKEN_TYPE)
               .setIssuer(ISSUER)
               .setIssuedAt(now)
               .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME))
               .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
  }

  public void validateToken(String token) throws JwtException, IllegalArgumentException {
    getClaimsFromToken(token);
  }

  public String getSubjectFromToken(String token) {
    return getClaimsFromToken(token).getSubject();
  }

  public String getRoleFromToken(String token) {
    return getClaimsFromToken(token).get("role").toString();
  }

  private Claims getClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
               .parseClaimsJws(token).getBody();
  }
}
