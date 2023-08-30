package com.example.koun.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;


    @Column(name = "raffle_quantity")
    private int raffleQuantity;


    private String artist;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    private String venue;


    @Column(name = "like_num")
    private int likeNum;

    @Column(name = "item_name")
    private String itemName;

    private String post;


    @Column(name = "age_requirement")
    private String ageRequirement;

    @Column(name = "running_time")
    private String runningTime;

    @Column(name = "upload_time")
    private LocalDate uploadTime;


    private String description;


    @OneToMany(mappedBy = "item")
    private List<Raffle> raffles = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Like> likes = new ArrayList<>();


    @Builder
    public Item(int raffleQuantity, String artist, LocalDateTime dateTime, String venue
            , int likeNum, String itemName, String post
            , String ageRequirement, String description, LocalDate uploadTime, String runningTime) {


        this.raffleQuantity = raffleQuantity;
        this.artist = artist;
        this.dateTime = dateTime;
        this.venue = venue;
        this.likeNum = likeNum;
        this.itemName = itemName;
        this.post = post;
        this.ageRequirement = ageRequirement;
        this.description = description;
        this.runningTime = runningTime;
        this.uploadTime = uploadTime;


    }

    public int findItemTotalCount() {
        return raffles.size();
    }

}
