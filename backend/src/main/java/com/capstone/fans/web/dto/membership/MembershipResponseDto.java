package com.capstone.fans.web.dto.membership;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MembershipResponseDto {
    private Long id;
    private LocalDateTime valid_date;
    private Long cashPerMonth;
    private String description;
    private byte[] membershipImage;


    @Builder
    public MembershipResponseDto(Long id, LocalDateTime valid_date, Long cashPerMonth, String description, byte[] membershipImage) {
        this.id = id;
        this.valid_date = valid_date;
        this.cashPerMonth = cashPerMonth;
        this.description = description;
        this.membershipImage = membershipImage;
    }

}