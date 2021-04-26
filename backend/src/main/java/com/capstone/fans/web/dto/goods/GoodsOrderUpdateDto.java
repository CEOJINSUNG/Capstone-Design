package com.capstone.fans.web.dto.goods;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GoodsOrderUpdateDto {
    private String address;
    private Long optionId;

    @Builder

    public GoodsOrderUpdateDto(String address, Long optionId) {
        this.address = address;
        this.optionId = optionId;
    }
}
