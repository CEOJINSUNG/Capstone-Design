package com.capstone.fans.web.controller.auth;


import com.capstone.fans.auth.JwtTokenProvider;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.auth.LogInDto;
import com.capstone.fans.web.dto.auth.SignUpDto;
import com.capstone.fans.web.dto.user.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class AuthController{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    // Create
    @PostMapping("/auth/signup")
    @ResponseBody
    public Long signup(@RequestBody SignUpDto signUpDto){
        System.out.println(signUpDto.getName());
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return userService.save(signUpDto);
    }

    @GetMapping("/auth/logout")
    public Long logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return 1L;
    }

    // Read
    @GetMapping("/auth/userInfo")
    public UserInfoDto userInfo(@AuthenticationPrincipal User user) {
        System.out.println(user.getAuthorities().stream().collect(Collectors.toList()).get(0).toString());
        return UserInfoDto.createDto(user);
    }


    @PostMapping("/auth/login")
    public String login(@RequestBody LogInDto logInDto) {
        User member = userService.findByEmail(logInDto.getEmail());
        if (!passwordEncoder.matches(logInDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getAuthorities().stream().map(a->a.toString()).collect(Collectors.toList()));
    }
}
