package com.example.koun.dto;

import lombok.Data;

@Data
public class ItemGetRequestDto {
    private String itemName;
    private Long userId;
}
