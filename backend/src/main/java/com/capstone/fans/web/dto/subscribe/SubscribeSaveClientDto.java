package com.capstone.fans.web.dto.subscribe;

import com.capstone.fans.domain.membership.Membership;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscribeSaveClientDto {
    private String payment_day;
    private Long total_cash;

    @Builder
    public SubscribeSaveClientDto(String payment_day, Long total_cash){
        this.payment_day = payment_day;
        this.total_cash = total_cash;
    }
}
