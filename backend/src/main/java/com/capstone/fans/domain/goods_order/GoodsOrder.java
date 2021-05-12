package com.capstone.fans.domain.goods_order;

import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class GoodsOrder extends BaseTimeEntity {
    public static final String DONE = "배송완료";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Goods goods;

    private String state;

    private String address;

    @ManyToOne
    @JoinColumn
    private Option option;

    private LocalDateTime shipped_date;

    @Builder
    public GoodsOrder(User user, Goods goods, String state, String address, Option option, LocalDateTime shipped_date) {
        this.user = user;
        this.goods = goods;
        this.state = state;
        this.address = address;
        this.option = option;
        this.shipped_date = shipped_date;
    }

    public void update(Option option, String address){
        this.option = option;
        this.address = address;
    }

    public void updateState(String state){
        if(this.state.equals(DONE))
            return;
        else if(state.equals(DONE))
            this.shipped_date = LocalDateTime.now();
        this.state = state;
    }
}
