package com.capstone.fans.domain.goods_order;

import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.fans.FanS;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class GoodsOrder extends BaseTimeEntity {
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

    public void update(){

    }
}
