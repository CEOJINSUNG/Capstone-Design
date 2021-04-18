package com.capstone.fans.domain.goods.option;


import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.goods.Goods;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
public class Option extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GOODS_IDSS")
    private Long goods_id;

    private String name;

    private Long costs;

    public Option(Long goods_id, String name, Long costs) {
        this.goods_id = goods_id;
        this.name = name;
        this.costs = costs;
    }
}
