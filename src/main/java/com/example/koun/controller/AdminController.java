package com.example.koun.controller;

import com.example.koun.domain.Item;
import com.example.koun.dto.ItemNameDto;
import com.example.koun.dto.ItemRequestDto;
import com.example.koun.dto.ItemResponseDto;
import com.example.koun.repository.ItemRepository;
import com.example.koun.service.ItemService;
import com.example.koun.service.RaffleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final RaffleService raffleService;
    private final ItemService itemService;



    @PostMapping("/raffle/draw/item")
    public ResponseEntity<Void> drawRaffleForItem(@RequestBody ItemNameDto requestDto) {
        // TODO: JWT 토큰 검증 및 관리자 권한 확인 로직

        //아이템 이름으로 조회하는 로직 필요
        ItemResponseDto itemResponseDto = itemService.findItemsByName(requestDto.getItemName());
        raffleService.drawRaffleForItem(itemResponseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
