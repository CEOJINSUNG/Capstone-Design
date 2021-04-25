package com.capstone.fans.web.controller.auth;


import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.auth.SignUpDto;
import com.capstone.fans.web.dto.user.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@RestController
public class AuthController{
    private final UserService userService;

    // Create
    @PostMapping("/auth/signup")
    public Long signup(@RequestBody SignUpDto signUpDto){
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
        return UserInfoDto.createDto(user);
    }

    //Update



}
