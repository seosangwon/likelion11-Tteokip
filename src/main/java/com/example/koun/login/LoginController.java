//package com.example.koun.login;
//
//
//import com.example.koun.dto.UserSaveRequestDto;
//import com.example.koun.dto.UserSaveResponseDto;
//import com.example.koun.login.auth.OAuthToken;
//import com.example.koun.login.auth.kakao.KakaoProfile;
//import com.example.koun.login.jwt.JwUtil;
//
//import com.example.koun.service.UserService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.*;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//
//public class LoginController {
//
//    @Autowired
//    private UserService userService;
//
//
//    @RequestMapping(value = "/loginRedirect", method = RequestMethod.GET)
//    public ResponseEntity<String> kakaoLogin(@RequestParam(value = "code", required = false) String code
//            , HttpServletResponse httpResponse) throws Exception {
//
//
//
//    }
//
//
//}
//
//

