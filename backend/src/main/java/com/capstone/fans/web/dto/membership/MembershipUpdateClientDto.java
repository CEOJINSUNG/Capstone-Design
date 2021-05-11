package com.capstone.fans.web.dto.membership;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MembershipUpdateClientDto {
    private String valid_date;

    private Long cashPerMonth;

    private String membershipName;

    private String description;

    private byte[] membershipImage;

    @Builder
    public MembershipUpdateClientDto(String valid_date, Long cashPerMonth, String membershipName, String description, byte[] membershipImage) {
        this.valid_date = valid_date;
        this.cashPerMonth = cashPerMonth;
        this.membershipName = membershipName;
        this.description = description;
        this.membershipImage = membershipImage;
    }
}
