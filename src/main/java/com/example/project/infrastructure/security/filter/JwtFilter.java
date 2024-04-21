package com.example.project.infrastructure.security.filter;

import com.example.project.infrastructure.JwtProvider;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;
  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String AUTHORIZATION_TYPE = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String jwtToken = getJwtFromRequest(request);

    try {
      jwtProvider.validateToken(jwtToken);
      UserDetails userDetails = getUserDetails(jwtProvider.getSubjectFromToken(jwtToken),
          jwtProvider.getRoleFromToken(jwtToken));
      Authentication authentication = getAuthentication(userDetails);

      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException | IllegalArgumentException e) {
      log.info("Exception : {}, Message : {}", e.getClass(), e.getMessage());
    }

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String[] excludePath = {"/api/signup", "/api/auth/login"};
    String path = request.getRequestURI();
    return Arrays.stream(excludePath).anyMatch(path::equals);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_TYPE)) {
      return bearerToken.substring(7);
    }
    return null;
  }

  private Authentication getAuthentication(UserDetails userDetails) {
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  private UserDetails getUserDetails(String userId, String role) {
    return User.builder().username(userId).password("").authorities(role).build();

  }
}
