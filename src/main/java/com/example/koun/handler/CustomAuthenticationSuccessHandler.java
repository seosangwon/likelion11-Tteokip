package com.example.koun.handler;

import com.example.koun.dto.TokenResponseDto;
import com.example.koun.dto.UserSaveResponseDto;
import com.example.koun.login.jwt.JwtUtil;
import com.example.koun.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // JWT 생성 및 반환 로직
        // 예: response.setHeader("Authorization", "Bearer " + jwt);

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String userEmail = oAuth2User.getAttribute("email"); // 사용자의 이메일 속성을 가져옵니다.
        UserSaveResponseDto user = userService.findUserByEmail(userEmail);


        // 필요한 추가 속성 추출
        // JWT 생성
        String refreshToken = jwtUtil.generateRefreshToken(user);
        String accessToken = jwtUtil.generateAccessToken(refreshToken);
        userService.saveRefreshToken(user.getId(), refreshToken);

        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .userId(user.getId())
                .build();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(tokenResponseDto);
        response.getWriter().write(jsonResponse);

        response.sendRedirect("/main.html");


    }
}