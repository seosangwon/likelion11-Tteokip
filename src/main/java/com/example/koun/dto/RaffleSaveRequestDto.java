package com.example.koun.dto;

import com.example.koun.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class RaffleSaveRequestDto {
    private int raffleCount;
    private String raffleDrawDate;
    private Long userId;
    private String itemName;
    private Long sectionId;




    public Raffle toEntity(User user, Item item, Section section) {
        return Raffle.builder()
                .raffleCount(raffleCount)
                .raffleDrawDate(convertToDateTime(raffleDrawDate))
                .user(user)
                .item(item)
                .section(section)
                .build();
    }

    // 문자열을 LocalDateTime으로 변환하는 메서드
    private LocalDateTime convertToDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }
}