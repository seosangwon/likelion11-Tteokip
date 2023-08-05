package com.example.koun.login;


import com.example.koun.domain.RoleType;
import com.example.koun.dto.UserResponseDto;
import com.example.koun.dto.UserSaveRequestDto;
import com.example.koun.login.auth.OAuthToken;
import com.example.koun.login.auth.kakao.KakaoProfile;
import com.example.koun.login.session.SessionManager;
import com.example.koun.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionManager sessionManager;






    @GetMapping("/")
    public String login(){
        return "loginTest";
    }



    @RequestMapping(value="/loginRedirect", method=RequestMethod.GET)
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code
    , HttpServletResponse httpResponse) throws Exception {
        System.out.println("카카오로부터 요청받은 인가코드 : " + code);


        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "2cf364356a3329e6b0fca033d6447fbd");
        params.add("redirect_uri", "http://localhost:8080/loginRedirect");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params,
            headers);

        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token", // https://{요청할 서버 주소}
            HttpMethod.POST, // 요청할 방식
            kakaoTokenRequest, // 요청할 때 보낼 데이터
            String.class // 요청 시 반환되는 데이터 타입
        );

        System.out.println(" 카카오 토큰 요청 완료: 토큰 요청에 대한 응답 :" + response);

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;

        try{
             oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        System.out.println("카카오 액세스 토큰 : "+oauthToken.getAccess_token());

        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new org.springframework.http.HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");



        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(params,
            headers2);

        ResponseEntity<String> response2 = rt2.exchange(

            "https://kapi.kakao.com/v2/user/me", // https://{요청할 서버 주소}
            HttpMethod.POST, // 요청할 방식
            kakaoProfileRequest2, // 요청할 때 보낼 데이터
            String.class // 요청 시 반환되는 데이터 타입
        );

        System.out.println("response2.getBody(): "+ response2.getBody());



        ObjectMapper objectMapper2= new ObjectMapper();
        KakaoProfile kakaoProfile = objectMapper2.readValue(response2.getBody(),
            KakaoProfile.class);
        try{
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }


        //USER 오브젝트 : userName , password, userEmail
        System.out.println("카카오 아이디: "+ kakaoProfile.getId());
        System.out.println("카카오 이메일: " + kakaoProfile.getKakao_account().getEmail());

        System.out.println("koun 유저네임: "+ kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("koun 이메일: " + kakaoProfile.getKakao_account().getEmail());
        System.out.println("userName : "+kakaoProfile.getKakao_account().getProfile().getNickname());

        //requestDto 생성
        UserSaveRequestDto kakaoUser = UserSaveRequestDto.builder()
            .userName(kakaoProfile.getKakao_account().getProfile().getNickname())
            .userEmail(kakaoProfile.getKakao_account().getEmail())
            .roleType(RoleType.USER)
            .isDeleted(false)
            .oauth("kakao")
            .build();


        // 가입자 혹은 비가입자 체크 해서 처리 -> 로그인 처리
        UserResponseDto originUser = userService.findOne(kakaoUser.getUserEmail());


        if (originUser.getUserName() == null) {
            Long id = userService.join(kakaoUser);
            // 새롭게 가입한 사용자의 정보를 세션에 저장
            sessionManager.createSession(userService.findOne(kakaoUser.getUserEmail()), httpResponse);
        } else {
            // 이미 가입한 사용자의 정보를 세션에 저장
            sessionManager.createSession(originUser,httpResponse );
        }

        System.out.println(originUser.getUserName() + originUser.getUserEmail());
        System.out.println("자동 로그인 시작");













        return "loginRedirect";

    }
















}