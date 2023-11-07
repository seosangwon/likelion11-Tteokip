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


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;


    @Column(name = "user_name", nullable = false)
    private String userName;


    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "user_birth")
    private LocalDateTime userBirth;

    private String provider;


    private String account;

    private char gender;

    @Column(name = "user_address")
    private String userAddress;


    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name="refresh_token")
    private String refreshToken;




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
            , String provider , String refreshToken  , RoleType roleType ) {


        this.userName = userName;
        this.password = password;
        this.email = userEmail;
        this.phoneNum = phoneNum;
        this.userBirth = userBirth;
        this.account = account;
        this.gender = gender;
        this.userAddress = userAddress;
        this.refreshToken = refreshToken;
        this.isDeleted = false;
        this.roleType = roleType;
        this.provider = provider;
    }

    public User updateUser(String name, String email) {
        this.userName = name;
        this.email = email;

        return this;
    }

    public User saveToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

}

