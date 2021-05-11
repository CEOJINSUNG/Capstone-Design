package com.capstone.fans.web.controller.membership;


import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.MembershipService;
import com.capstone.fans.web.dto.membership.MembershipResponseDto;
import com.capstone.fans.web.dto.membership.MembershipSaveDto;
import com.capstone.fans.web.dto.membership.MembershipUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MembershipController {
    private final MembershipService membershipService;


    @PostMapping("/membership/save")
    public Long saveMembership(@RequestBody MembershipSaveDto membershipSaveDto, @AuthenticationPrincipal User user){
        return membershipService.saveMembership(membershipSaveDto, user);
    }

    @PutMapping("/membership/update/{membership_id}")
    public Long updateMembership(@PathVariable Long membership_id, @RequestBody MembershipUpdateDto membershipUpdateDto, @AuthenticationPrincipal User user){
        return membershipService.updateMembership(membership_id, membershipUpdateDto, user);
    }

    @GetMapping("/membership/{membership_id}")
    public MembershipResponseDto getMembership(@PathVariable Long membership_id){
        return membershipService.findById(membership_id);
    }


    @GetMapping("/membership/club/{club_id}")
    public List<MembershipResponseDto> getMembershipList(@PathVariable Long club_id){
        return membershipService.getMembershipList(club_id);
    }

    @DeleteMapping("/membership/club/{membership_id}")
    public Long deleteMembership(@PathVariable Long membership_id, User user) {
        return membershipService.deleteMembership(membership_id, user);
    }
}
