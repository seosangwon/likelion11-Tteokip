package com.example.koun.controller;

import com.example.koun.dto.LikeRequestDto;
import com.example.koun.repository.LikeRepository;
import com.example.koun.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@Controller
@RequestMapping("api/like")
@RequiredArgsConstructor
public class LikeApiController {
    private final LikeService likeService;


    //좋아요 생성
    @PostMapping("create")
    public ResponseEntity<Long> createLike(@RequestBody LikeRequestDto requestDto){
        Long likeId = likeService.joinLike(requestDto);

        return new ResponseEntity<>(likeId, HttpStatus.CREATED);
    }




}
