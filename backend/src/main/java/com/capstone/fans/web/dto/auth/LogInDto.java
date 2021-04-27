package com.capstone.fans.web.dto.auth;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogInDto {
    private String email;
    private String password;

    @Builder
    public LogInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
