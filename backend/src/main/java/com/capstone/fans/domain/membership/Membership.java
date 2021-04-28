package com.capstone.fans.domain.membership;

import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.fans.FanS;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Entity
public class Membership extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Club club;

    private LocalDateTime valid_date;

    private Long cashPerMonth;

    private String description;

    private byte[] membershipImage;

    @Builder
    public Membership(Club club, LocalDateTime valid_date, Long cashPerMonth, String description, byte[] membershipImage) {
        this.club = club;
        this.valid_date = valid_date;
        this.cashPerMonth = cashPerMonth;
        this.description = description;
        this.membershipImage = membershipImage;
    }
}
