package com.example.koun.dto;

import com.example.koun.domain.User;
import lombok.Data;

@Data
public class UserInfoDto {

    private String name;
    private String email;

    public UserInfoDto(String name, String email) {
        this.name = name;
        this.email = email;
    }


}
