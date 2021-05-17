package com.capstone.fans.web.dto.membership;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class MembershipUpdateDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime valid_date;

    private Long cashPerMonth;

    private String membershipName;

    private String description;

    private String membershipImage;

    @Builder
    public MembershipUpdateDto(LocalDateTime valid_date, Long cashPerMonth, String membershipName, String description, String membershipImage) {
        this.valid_date = valid_date;
        this.cashPerMonth = cashPerMonth;
        this.membershipName = membershipName;
        this.description = description;
        this.membershipImage = membershipImage;
    }
}
