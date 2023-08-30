package com.example.koun.dto;

import com.example.koun.domain.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ItemRequestDto {
    private int raffleQuantity;
    private String artist;
    private String dateTime;
    private String venue;
    private int likeNum;
    private String itemName;
    private String description;
    private String runningTime;
    private String post;
    private String uploadTime;
    private String ageRequirement;

    // 1. 프론트에서 itemRequestDto에 대한 파라미터 정보를 받는다.
    // 2. 빌더 패턴으로 requestDto 생성
    @Builder
    public ItemRequestDto(int raffleQuantity, String artist, String dateTime, String venue, int likeNum, String itemName, String description, String runningTime, String post, String uploadTime) {
        this.raffleQuantity = raffleQuantity;
        this.artist = artist;
        this.dateTime = dateTime;
        this.venue = venue;
        this.likeNum = likeNum;
        this.itemName = itemName;
        this.description = description;
        this.runningTime = runningTime;
        this.post = post;
        this.uploadTime = uploadTime;
    }


    // DB에 저장될 때는 toEntity 함수를 사용해서 저장 , 반환할 때는 id 번호 줌
    public Item toEntity() {
        return Item.builder()
                .raffleQuantity(raffleQuantity)
                .artist(artist)
                .dateTime(convertToDateTime(dateTime))
                .venue(venue)
                .likeNum(likeNum)
                .itemName(itemName)
                .description(description)
                .runningTime(runningTime)
                .post(post)
                .uploadTime(convertToDate(uploadTime))
                .ageRequirement(ageRequirement)
                .build();
    }

    // 문자열을 LocalDateTime으로 변환하는 메서드
    public static LocalDateTime convertToDateTime(String dateString) {
        if (dateString == null) {
            // 혹시 널 값을 가지면 기본값이나 예외 처리를 해줄 수 있습니다.
            // 여기서는 널 값을 가지면 현재 시간을 반환하도록 처리했습니다.
            return LocalDateTime.now();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }

    public static LocalDate convertToDate(String dateTime) {

        if (dateTime == null) {
            // 혹시 널 값을 가지면 기본값이나 예외 처리를 해줄 수 있습니다.
            // 여기서는 널 값을 가지면 현재 시간을 반환하도록 처리했습니다.
            return LocalDate.now();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateTime, formatter);
    }
}