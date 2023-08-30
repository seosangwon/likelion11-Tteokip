package com.example.koun.dto;

import com.example.koun.domain.Item;
import java.time.format.DateTimeFormatter;
import lombok.Data;

// 엔티티에서 값을 불러받을 때 사용되는 DTO
@Data
public class ItemResponseDto {
    private Long id;
    private int raffleQuantity; // 확장성
    private String artist;
    private String dateTime;
    private String venue;
    private int likeNum; // 확장성
    private String itemName;
    private String description; // 확장성
    private String runningTime;
    private String post;
    private String uploadTime;
    private String ageRequirement;

    //파라미터로 Entity를 받으면 Dto로 변환
    public ItemResponseDto(Item item){
        this.id = item.getId();
        this.raffleQuantity = item.getRaffleQuantity();
        this.artist = item.getArtist();
        this.dateTime = item.getDateTime().format(formatter);
        this.venue = item.getVenue();
        this.likeNum = item.getLikeNum();
        this.itemName = item.getItemName();
        this.description = item.getDescription();
        this.runningTime = item.getRunningTime();
        this.post = item.getPost();
        this.uploadTime = item.getUploadTime().format(formatter2);
        this.ageRequirement=item.getAgeRequirement();
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");


}