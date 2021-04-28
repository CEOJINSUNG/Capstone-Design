package com.capstone.fans.web.controller.subscribe;


import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.SubscribeService;
import com.capstone.fans.web.dto.subscribe.SubscribeListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class SubscribeController {
    private final SubscribeService subscribeService;

    @PostMapping("/membership/subscibe/{membership_id}")
    public Long saveSubscrible(@PathVariable Long membership_id, @AuthenticationPrincipal User user){
            return subscribeService.saveSubscribe(membership_id, user);
    }

    @DeleteMapping("/membership/subscribe/{membership_id}")
    public Long cancelSubscribe(@PathVariable Long membership_id, @AuthenticationPrincipal User user){
        return subscribeService.cancelSubscribe(membership_id, user);
    }

    @GetMapping("/membership/subscribe")
    public List<SubscribeListDto> getSubscribeList(@AuthenticationPrincipal User user){
        return subscribeService.getSubscribeList(user);
    }
}
