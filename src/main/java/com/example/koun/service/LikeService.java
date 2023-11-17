package com.example.koun.service;

import com.example.koun.domain.Item;
import com.example.koun.domain.Like;
import com.example.koun.domain.User;
import com.example.koun.dto.LikeRequestDto;
import com.example.koun.repository.ItemRepository;
import com.example.koun.repository.LikeRepository;
import com.example.koun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;


    //DB에 저장
    @Transactional
    public Long joinLike(LikeRequestDto likeRequestDto) {
        Long itemId = likeRequestDto.getItemId();
        Long userId = likeRequestDto.getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다"));


        Like like = likeRequestDto.toEntity(user, item);

        return likeRepository.save(like).getId();


    }



    // 좋아요 삭제
    public void deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + likeId));
        likeRepository.delete(like);
    }


}
