package com.example.koun.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/html")
public class UserController {


    @GetMapping("")
    public String s3Start() {
        return "html/main";
    }

    @GetMapping("/main.html")
    public String main() {
        return "html/main";
    }

    @GetMapping("/login.html")
    public String formLogin(){
        return "html/login";
    }

//
//    @GetMapping("/formLogin")
//    public String loginPage() {
//        return "formLogin";
//    }
//

    @GetMapping("/mypage.html")
    public String myPage() {
        return "html/mypage";
    }
    @GetMapping("signup.html")
    public String signUp(){
        return "html/signup";
    }
    @GetMapping("pay.html")
    public String pay(){
        return "html/pay";
    }
    @GetMapping("cancel.html")
    public String cancel(){
        return "html/cancel";
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





}
