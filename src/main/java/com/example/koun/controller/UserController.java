package com.example.koun.controller;

import com.example.koun.login.jwt.JwtUtil;
import com.example.koun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {


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


//    @GetMapping("/mypage.html")
//    public String myPage() {
//        return "html/mypage";
//    }

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





}
