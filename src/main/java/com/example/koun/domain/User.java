package com.example.koun.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
public class User {

    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;



    @Column(name="user_name",nullable=false)
    private String userName;


    private String password;

    @Column(name="user_email",nullable=false,unique = true)
    private String userEmail;

    @Column(name="phone_num")
    private String phoneNum;

    @Column(name="user_birth")
    private LocalDateTime userBirth;

    private  String oauth;


    private String account;

    private char gender;

    @Column(name="user_address")
    private String userAddress;


    @Column(name="is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;




    @OneToMany(mappedBy = "user")
    private List<Raffle> raffles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();


    @Builder
    public User(String userName, String password, String userEmail, String phoneNum
                , LocalDateTime userBirth, String account, char gender, String userAddress

                , String oauth) {



        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.phoneNum = phoneNum;
        this.userBirth = userBirth;
        this.account = account;
        this.gender = gender;
        this.userAddress = userAddress;

        this.isDeleted = false;
        this.roleType = RoleType.USER;
        this.oauth = oauth;
    }

}

