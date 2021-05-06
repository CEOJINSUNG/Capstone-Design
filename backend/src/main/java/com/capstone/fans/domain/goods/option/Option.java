package com.capstone.fans.domain.goods.option;


import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.goods.Goods;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "GoodsOption")
public class Option extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn
    private Goods goods;

    private String name;

    private Long costs;

    @Builder
    public Option(Goods goods, String name, Long costs) {
        this.goods = goods;
        this.name = name;
        this.costs = costs;
    }
}
