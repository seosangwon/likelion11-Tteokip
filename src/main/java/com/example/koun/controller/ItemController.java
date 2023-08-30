package com.example.koun.controller;

import com.example.koun.dto.ItemRequestDto;
import com.example.koun.dto.SectionSaveRequestDto;
import com.example.koun.service.ItemService;
import com.example.koun.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/section")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final SectionService sectionService;

    @GetMapping("/register")
    public String registerForm() {
        return "ItemAndSection";
    }

    @PostMapping("/register/save")
    public String saveSection(ItemRequestDto requestDto) {
        System.out.println("테스트 dto 이름" + requestDto.getItemName());
        itemService.joinItem(requestDto);
        return "ItemAndSection";
    }

    @PostMapping("/save")
    public String saveSection(SectionSaveRequestDto requestDto) {
        sectionService.joinSection(requestDto);
        return "ItemAndSection";
    }
}
