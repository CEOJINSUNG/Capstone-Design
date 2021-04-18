package com.capstone.fans.domain.goods_order;

import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.user.fans.FanS;
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
    private FanS fanS;

    @ManyToOne
    @JoinColumn
    private Goods goods;

    private String state;

    private LocalDateTime shipped_date;
}
