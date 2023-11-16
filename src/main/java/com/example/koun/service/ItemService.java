package com.example.koun.service;

import com.example.koun.domain.Item;
import com.example.koun.domain.Like;
import com.example.koun.domain.Raffle;
import com.example.koun.domain.User;
import com.example.koun.dto.ItemGetRequestDto;
import com.example.koun.dto.ItemRequestDto;
import com.example.koun.dto.ItemResponseDto;
//import com.example.koun.dto.ItemUpdateRequestDto;
import com.example.koun.repository.ItemRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.koun.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    // 새로운 아이템 등록
    @Transactional
    public Long joinItem(ItemRequestDto itemRequestDto) {
        Item item = itemRequestDto.toEntity(); // ItemDto를 Item 엔티티로 변환
        return itemRepository.save(item).getId();
    }


    // 아이템 정보 업데이트
    @Transactional
//    public void updateItem(Long itemId, ItemUpdateRequestDto itemUpdateRequestDto) {
//        Item item = itemRepository.findById(itemId)
//            .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + itemId));
//
//        // ItemDto에서 받은 필드들로 Item 엔티티를 업데이트
//        item.updateItem(itemUpdateRequestDto);
//    }

    // 아이템 삭제
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + itemId));
        itemRepository.delete(item);
    }


    // 아이템 이름,userId로 조회
    @Transactional(readOnly = true)
    public ItemResponseDto findItemByGetRequestDto(ItemGetRequestDto requestDto) {
        String itemName = requestDto.getItemName();
        Item item = itemRepository.findByItemName(itemName)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다 name" + itemName));
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        List<Like> likes = user.getLikes();

        Optional<Like> likeOptional = likes.stream()
                .filter(like -> like.getItem().getId().equals(item.getId()))
                .findFirst();

        ItemResponseDto responseDto = new ItemResponseDto(item);

        // likeOptional이 존재하면, userLike를 true로 설정하고 likeId를 설정합니다.
        if (likeOptional.isPresent()) {
            responseDto.setUserLike(true);
            responseDto.setLikeId(likeOptional.get().getId()); // Like 객체의 ID를 설정
        } else {
            responseDto.setUserLike(false);
        }

        return responseDto;


    }

    //아이템 이름 으로 조회
    @Transactional(readOnly = true)
    public ItemResponseDto findItemsByName(String itemName){
        Item item = itemRepository.findByItemName(itemName).orElseThrow(()-> new IllegalArgumentException("아이템이 없습니다"));
        return new ItemResponseDto(item);


    }

    // 모든 아이템 조회
    @Transactional(readOnly = true)
    public List<ItemResponseDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
    }

    //user의 좋아요 아이템 조회
    @Transactional(readOnly = true)
    public List<ItemResponseDto> getUserLikeItemList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        List<Like> likes = user.getLikes();
        List<Raffle> raffles = user.getRaffles();
        Set<Long> raffleItemIds = raffles.stream()
                .map(Raffle::getItem)
                .map(Item::getId)
                .collect(Collectors.toSet());

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        for (Like like : likes) {
            Item item = like.getItem();
            ItemResponseDto dto = new ItemResponseDto(item);
            dto.setUserLike(true);

            // 응모를 햇는지 안햇는지 체크
            if (raffleItemIds.contains(item.getId())) {
                dto.setUserRaffle(true);
            } else {
                dto.setUserRaffle(false);
            }

            itemResponseDtos.add(dto);
        }

        return itemResponseDtos;
    }


    // 인기 top1~10 조회
    public Page<ItemResponseDto> getTopLikes(Pageable pageable) {
        Page<Item> topItems = itemRepository.findTopLikedItems(pageable);
        return topItems.map(this::convertToDto);
    }

    private ItemResponseDto convertToDto(Item item) {
        // Item 객체를 ItemResponseDto 객체로 변환하는 로직
        return new ItemResponseDto(item);
    }




    // 신규 top1~10 조회
    public Page<ItemResponseDto> getNewTopLikes(Pageable pageable) {
        Page<Item> newTopItems = itemRepository.findNewTopLikedItems(pageable);
        return newTopItems.map(this::convertToDto2);
    }

    private ItemResponseDto convertToDto2(Item item) {
        // Item 객체를 ItemResponseDto 객체로 변환하는 로직
        return new ItemResponseDto(item);
    }










// //   신규 top10 아이템 조회
//    @Transactional(readOnly = true)
//    public List<ItemResponseDto> getRecentItems() {
//        List<Item> items= itemRepository.findTop10ByOrderByUploadTimeDesc((Pageable) PageRequest.of(0, 10));
//        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
//        for(Item item : items){
//            ItemResponseDto dto = new ItemResponseDto(item);
//            itemResponseDtos.add(dto);
//        }
//        return itemResponseDtos;
//    }


}