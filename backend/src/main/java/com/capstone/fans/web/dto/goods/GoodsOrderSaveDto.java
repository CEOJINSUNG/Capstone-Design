package com.capstone.fans.web.dto.goods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GoodsOrderSaveDto {
    private String adress;
    private Long option_id;
    private Long goods_id;

    @Builder
    public GoodsOrderSaveDto(String adress, Long option_id) {
        this.adress = adress;
        this.option_id = option_id;
    }
}
