package com.capstone.fans.web.dto.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogInDto {
    private String email;
    private String password;
}
