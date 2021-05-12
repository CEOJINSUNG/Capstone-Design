package com.capstone.fans.web.dto.subscribe;

import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.subscribe.Subscribe;
import com.capstone.fans.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubscribeSaveDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime payment_day;
    private Long total_cash;
    private Membership membership;

    @Builder
    public SubscribeSaveDto(LocalDateTime payment_day, Long total_cash){
        this.payment_day = payment_day;
        this.total_cash = total_cash;
    }

    public Subscribe toEntity(User user, Membership membership){
        return Subscribe.builder()
                .user(user)
                .membership(membership)
                .payment_date(payment_day)
                .total_cash(total_cash)
                .build();
    }
}
