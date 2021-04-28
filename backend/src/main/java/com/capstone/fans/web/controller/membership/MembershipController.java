package com.capstone.fans.web.controller.membership;


import com.capstone.fans.service.MembershipService;
import com.capstone.fans.web.dto.membership.MembershipResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
public class MembershipController {
    private final MembershipService membershipService;







}
