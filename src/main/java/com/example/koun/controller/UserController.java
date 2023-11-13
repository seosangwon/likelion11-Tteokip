package com.example.koun.controller;

import com.example.koun.config.AppConfig;
import com.example.koun.domain.User;
import com.example.koun.dto.TokenResponseDto;
import com.example.koun.dto.UserSaveRequestDto;
import com.example.koun.dto.UserSaveResponseDto;
import com.example.koun.login.jwt.JwtUtil;
import com.example.koun.service.UserService;
import com.google.common.base.CharMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String s3Start() {
        return "html/main";
    }

    @GetMapping("/main.html")
    public String main() {
        return "html/main";
    }

    @GetMapping("/formLogin")
    public String loginPage() {
        return "formLogin";
    }


    @GetMapping("/mypage.html")
    public String myPage() {
        return "html/mypage";
    }

    @GetMapping("/detail.html")
    public String detail() {
        return "html/detail";
    }

    @GetMapping("/raffle.html")
    public String raffle() {
        return "html/raffle";
    }

    @GetMapping("/resultPopup.html")
    public String result() {
        return "html/resultPopup";
    }


    @GetMapping("/loginTest.html")
    public String loginTest() {
        return "loginTest";
    }

    @GetMapping("admin/ItemAndSection")
    public String itemCreate(){
        return "ItemAndSection";
    }
    @GetMapping("/admin/RaffleDraw")
    public String raffleDraw(){
        return "RaffleDraw";
    }


    //폼 로그인 회원가입
    @PostMapping("/api/signup")
    public ResponseEntity<Void> formLoginCreate(@RequestBody UserSaveRequestDto requestDto) {

        if (userService.existsByEmail(requestDto.getUserEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }
        Long userId = userService.join(requestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    //폼 로그인 하기
    @PostMapping("/api/form_login")
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
                .build();

        return new ResponseEntity<>(tokenResponseDto, HttpStatus.CREATED);

    }

    public boolean confirmUser(String password, String dbPassword) {
        return passwordEncoder.matches(password, dbPassword);
    }



    @GetMapping("/user/logout")
    public void logOut() {


    }


}
