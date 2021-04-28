package com.capstone.fans.web.dto.subscribe;


import com.capstone.fans.web.dto.membership.MembershipResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubscribeListDto {
    private Long id;
    private MembershipResponseDto membershipResponseDto;
    private Long total_cash;
    private LocalDateTime payment_date;

    @Builder
    public SubscribeListDto(Long id, MembershipResponseDto membershipResponseDto, Long total_cash, LocalDateTime payment_date) {
        this.id = id;
        this.membershipResponseDto = membershipResponseDto;
        this.total_cash = total_cash;
        this.payment_date = payment_date;
    }
}
