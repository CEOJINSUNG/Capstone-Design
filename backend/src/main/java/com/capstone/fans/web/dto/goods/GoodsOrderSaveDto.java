package com.capstone.fans.web.dto.goods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GoodsOrderSaveDto {
    private String address;
    private Long option_id;
    private Long goods_id;

    @Builder
    public GoodsOrderSaveDto(String address, Long option_id, Long goods_id) {
        this.address = address;
        this.option_id = option_id;
        this.goods_id = goods_id;
    }
}
