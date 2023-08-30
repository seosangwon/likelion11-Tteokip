package com.example.koun.service;


import com.example.koun.repository.UserRepository;
import com.example.koun.domain.User;

import com.example.koun.dto.UserSaveResponseDto;

import com.example.koun.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    //회원가입
    @Transactional

    public Long join(UserSaveRequestDto requestDto) {

        return userRepository.save(requestDto.toEntity()).getId();
    }

    //회원찾기
    @Transactional(readOnly = true)

    public UserSaveResponseDto findUser(String userEmail) {
        try {
            User entity = userRepository.findByUserEmail(userEmail)
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. "));

            return new UserSaveResponseDto(entity);
        } catch (IllegalArgumentException e) {
            return new UserSaveResponseDto();

        }
    }


}
