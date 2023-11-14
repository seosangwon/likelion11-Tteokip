package com.example.koun.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long usrId;


    @Builder
    public TokenResponseDto(String accessToken, String refreshToken , Long usrId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.usrId = usrId;
    }

}