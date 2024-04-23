package com.example.project.infrastructure.security.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.project.domain.auth.exception.AuthErrorCode;
import com.example.project.presentation.common.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    setResponse(response);
  }

  private void setResponse(HttpServletResponse response)
      throws IOException {
    response.setContentType(APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    objectMapper.writeValue(
        response.getOutputStream(),
        ErrorResponse.of(AuthErrorCode.ACCESS_DENIED));
  }
}
