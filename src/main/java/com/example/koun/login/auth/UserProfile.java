package com.example.koun.login.auth;

import com.example.koun.domain.RoleType;
import com.example.koun.domain.User;
import lombok.Data;
import lombok.Setter;

@Data
public class UserProfile {
    private String email;
    private String name;
    private String provider;

    public User toEntity(){
        return User.builder()
                .userName(this.name)
                .provider(this.provider)
                .userEmail(this.email)
                .roleType((RoleType.USER))
                .build();
    }


}
