package com.example.koun.login.auth;

import com.example.koun.domain.User;
import com.example.koun.dto.TokenResponseDto;
import com.example.koun.dto.UserSaveResponseDto;
import com.example.koun.login.jwt.JwtUtil;
import com.example.koun.repository.UserRepository;
import com.example.koun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OAuth2Service implements org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("loadUser method called");
        String token = userRequest.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + token); // kakao access token

        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);


        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); //PK가 되는 정보

        Map<String, Object> attributes = oAuth2User.getAttributes();


        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);
        userProfile.setProvider(registrationId);

        updateOrSaveUser(userProfile);

        Map<String, Object> customAttribute =
                getCustomAttribute(registrationId, userNameAttributeName, attributes, userProfile);


        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER1")),
                customAttribute,
                userNameAttributeName);


    }

    public Map getCustomAttribute(String registrationId,
                                  String userNameAttributeName,
                                  Map<String, Object> attributes,
                                  UserProfile userProfile) {
        Map<String, Object> customAttribute = new ConcurrentHashMap<>();

        customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
        customAttribute.put("oauth", registrationId);
        customAttribute.put("name", userProfile.getName());
        customAttribute.put("email", userProfile.getEmail());

        return customAttribute;
    }


    public ResponseEntity<Map<String, String>> updateOrSaveUser(UserProfile userProfile) {
        User user = userRepository
                .findByEmail(userProfile.getEmail())
                .map(value -> value.updateUser(userProfile.getName(), userProfile.getEmail()))
                .orElse(userProfile.toEntity());
        UserSaveResponseDto userDto = new UserSaveResponseDto(user);
        String refreshToken = jwtUtil.generateRefreshToken(userDto);
        String accessToken = jwtUtil.generateAccessToken(refreshToken);
        System.out.println(accessToken);
        userRepository.save(user);
        userService.saveRefreshToken(user.getId(),refreshToken );

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return ResponseEntity.ok(tokens);
    }

}
