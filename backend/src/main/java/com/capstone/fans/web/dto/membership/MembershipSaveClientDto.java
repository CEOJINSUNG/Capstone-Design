package com.capstone.fans.web.dto.membership;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MembershipSaveClientDto {
    private String valid_date;

    private Long cashPerMonth;

    private String membershipName;

    private String description;

    private String membershipImage;

    @Builder
    public MembershipSaveClientDto (String valid_date, Long cashPerMonth, String membershipName, String description, String image){
        this.valid_date = valid_date;
        this.cashPerMonth = cashPerMonth;
        this.membershipImage = image;
        this.membershipName = membershipName;
        this.description = description;
    }
}
