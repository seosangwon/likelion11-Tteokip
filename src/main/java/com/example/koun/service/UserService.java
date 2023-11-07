package com.example.koun.service;


import com.example.koun.repository.UserRepository;
import com.example.koun.domain.User;

import com.example.koun.dto.UserSaveResponseDto;

import com.example.koun.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional

    public Long join(UserSaveRequestDto requestDto) {


        String password= requestDto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        requestDto.setPassword(encodedPassword);

        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public UserSaveResponseDto findOneById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 id가 없습니다"));
        return new UserSaveResponseDto(user);
    }


    //userEmail로 회원찾기
    @Transactional(readOnly = true)

    public UserSaveResponseDto findUserByEmail(String userEmail) {
        try {
            User entity = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. "));

            return new UserSaveResponseDto(entity);
        } catch (IllegalArgumentException e) {
            return new UserSaveResponseDto();

        }
    }

    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        user.saveToken(refreshToken);


    }


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일이 없습니다"));


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());


    }


}
