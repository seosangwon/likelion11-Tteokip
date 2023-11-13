package com.example.koun.login.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.koun.domain.RoleType;
import com.example.koun.dto.UserSaveResponseDto;
import com.example.koun.repository.UserRepository;
import com.example.koun.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserService userService;
    // 토큰의 서명에 사용될 비밀 키
    private static final String SECRET_KEY = "koun1234";  // 이 키는 안전한 곳에 저장되어야 하며, 변경될 수 있습니다.

    // 토큰 만료 시간 (예: 15분)
    private static final long ACCESS_EXPIRATION_TIME = 900_000;  // 15 * 60 * 1000
    private static final long REFRESH_EXPIRATION_TIME = 86_400_000;  // 하루

    public String generateRefreshToken(UserSaveResponseDto user) {
        // 사용자의 기본 정보를 포함한 리프레시 토큰을 생성합니다.
        String roleType = user.getRoleType().toString();

        return JWT.create()
                .withSubject(user.getUserEmail()) // 사용자 이메일을 subject로 설정
                .withClaim("userId", user.getId().toString()) // 사용자 ID를 별도의 클레임으로 추가
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .withClaim("ROLE", roleType)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String generateAccessToken(String refreshToken) {
        // 리프레시 토큰을 검증하고, 액세스 토큰을 생성합니다.
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(refreshToken);
        String userEmail = decodedJWT.getSubject();
        String userId = decodedJWT.getClaim("userId").asString();
        String roleType = decodedJWT.getClaim("ROLE").asString();

        return JWT.create()
                .withSubject(userEmail)
                .withClaim("userId",userId)
                .withClaim("ROLE",roleType)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String resolveToken(HttpServletRequest request) {
        // HTTP 요청 헤더에서 "Authorization" 값을 추출하여 토큰을 반환합니다.
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String accessToken) {
        // 액세스 토큰을 검증하고, 인증 객체를 생성하여 반환합니다.
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(accessToken);
        String userEmail = decodedJWT.getSubject();
        String role = decodedJWT.getClaim("ROLE").asString();

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userEmail, "", authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }


}

