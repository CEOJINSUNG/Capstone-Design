package com.capstone.fans.domain.subscribe;

import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.user.fans.FanS;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Subscribe extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn
    private Membership membership;

    @ManyToOne
    @JoinColumn
    private FanS fans;

    private Long total_cash;

    private LocalDateTime payment_date;

    @Builder
    public Subscribe(Membership membership, FanS fans, Long total_cash, LocalDateTime payment_date) {
        this.membership = membership;
        this.fans = fans;
        this.total_cash = total_cash;
        this.payment_date = payment_date;
    }
}
