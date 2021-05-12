package com.capstone.fans.web.dto.subscribe;

import com.capstone.fans.domain.membership.Membership;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubscribeResponseDto {
    private Long id;
    private Long total_cash;

    private LocalDateTime payment_date;
    private Membership membership;


    @Builder
    SubscribeResponseDto (Long id, Long total_cash, LocalDateTime payment_date, Membership membership){
        this.id = id;
        this.total_cash = total_cash;
        this.payment_date = payment_date;
        this.membership = membership;
    }
}
