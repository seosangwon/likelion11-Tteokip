package com.example.koun.config;

import com.example.koun.domain.RoleType;
import com.example.koun.domain.User;
import com.example.koun.dto.UserSaveResponseDto;
import com.example.koun.handler.CustomAuthenticationSuccessHandler;
import com.example.koun.login.auth.OAuth2Service;
import com.example.koun.login.jwt.JwtAuthenticationFilter;
import com.example.koun.login.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2Service oAuth2Service;
    private final JwtUtil jwtUtil;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterAfter(jwtAuthenticationFilter, LogoutFilter.class)

                .csrf((csrf) -> csrf.disable())
                .authorizeRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/loginTest.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/main.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/detail.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/raffle.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/mypage.html")).hasAuthority("ROLE_USER")
                        .requestMatchers(new AntPathRequestMatcher("/resultPopup.html")).hasAuthority("ROLE_USER")
                        .requestMatchers(new AntPathRequestMatcher("/admin/RaffleDraw")).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/admin/ItemAndSection")).hasAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                                .loginPage("/formLogin")
//                        .defaultSuccessUrl("/")
//                        .usernameParameter("email")
//                        .passwordParameter("password")
                                .permitAll()

                )
                .logout(customizer -> customizer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("remember-me")
                        .permitAll()
                )


                .oauth2Login()
                .defaultSuccessUrl("/main.html")
                .successHandler(customAuthenticationSuccessHandler)
                .userInfoEndpoint() // 사용자가 로그인에 성공하였을 경우,
                .userService(oAuth2Service) // 해당 서비스 로직을 타도록 설정


                .and()


                .and().build();


    }


}