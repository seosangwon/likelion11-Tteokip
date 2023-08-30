package com.example.koun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String s3Start() {
        return "html/main";
    }

    @GetMapping("/main.html")
        public String main(){
            return "html/main";
        }


    @GetMapping("/mypage.html")
    public String myPage(){
        return "html/mypage";
    }

    @GetMapping("/detail.html")
    public String detail(){
        return "html/detail";
    }
    @GetMapping("/raffle.html")
    public String raffle(){
        return "html/raffle";
    }
    @GetMapping("/resultPopup.html")
    public String result(){
        return "html/resultPopup";
    }




}
