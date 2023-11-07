package com.example.koun.dto;


import com.example.koun.domain.RoleType;
import com.example.koun.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveResponseDto {

    private Long id;
    private String userName;
    private String userEmail;
    private RoleType roleType;
    private String password;


    //파라미터로 Entity를 받으면 Dto로 변환
    public UserSaveResponseDto(User entity){
        this.id= entity.getId();
        this.userEmail= entity.getEmail();
        this.userName= entity.getUserName();
        this.roleType = entity.getRoleType();
        this.password=entity.getPassword();
    }


}
