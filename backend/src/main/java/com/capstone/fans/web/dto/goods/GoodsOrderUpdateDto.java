package com.capstone.fans.web.dto.goods;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GoodsOrderUpdateDto {
    private String address;
    private Long optionId;
    private Long orderId;


    @Builder
    public GoodsOrderUpdateDto(String address, Long optionId, Long orderId) {
        this.address = address;
        this.optionId = optionId;
        this.orderId = orderId;
    }
}
