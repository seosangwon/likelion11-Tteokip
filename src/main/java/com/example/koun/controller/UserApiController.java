package com.example.koun.controller;

import com.example.koun.dto.TokenResponseDto;
import com.example.koun.dto.UserInfoDto;
import com.example.koun.dto.UserSaveRequestDto;
import com.example.koun.dto.UserSaveResponseDto;
import com.example.koun.login.jwt.JwtUtil;
import com.example.koun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //폼 로그인 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Void> formLoginCreate(@RequestBody UserSaveRequestDto requestDto) {

        if (userService.existsByEmail(requestDto.getUserEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }
        Long userId = userService.join(requestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    //폼 로그인 하기
    @PostMapping("/form_login")
    public ResponseEntity<TokenResponseDto> formLoginCheck(@RequestBody UserSaveRequestDto requestDto) {
        UserSaveResponseDto user = userService.findUserByEmail(requestDto.getUserEmail());
        if (!confirmUser(requestDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }


        String refreshToken = jwtUtil.generateRefreshToken(user);
        String accessToken = jwtUtil.generateAccessToken(refreshToken);
        userService.saveRefreshToken(user.getId(), refreshToken);

        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .userId(user.getId())
                .build();

        return new ResponseEntity<>(tokenResponseDto, HttpStatus.CREATED);

    }

    public boolean confirmUser(String password, String dbPassword) {
        return passwordEncoder.matches(password, dbPassword);
    }


    //user 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDto> getUser(@PathVariable Long userId ){
        UserSaveResponseDto userSaveResponseDto = userService.findOneById(userId);
        UserInfoDto userInfoDto = new UserInfoDto(userSaveResponseDto.getUserName(), userSaveResponseDto.getUserEmail());

        return new ResponseEntity<>(userInfoDto, HttpStatus.CREATED);
    }





}
