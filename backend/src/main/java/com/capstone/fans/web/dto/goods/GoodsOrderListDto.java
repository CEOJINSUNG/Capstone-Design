package com.capstone.fans.web.dto.goods;

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
public class GoodsOrderListDto {
    private Long id;
    private byte[] image;
    private String state;
    private String address;
    private String option_description;
    private Long cost;
    private LocalDateTime shipped_date;
    private LocalDateTime created_date;

    @Builder
    public GoodsOrderListDto(Long id, byte[] image, String state, String address, String option_description, Long cost, LocalDateTime shipped_date, LocalDateTime created_date) {
        this.id = id;
        this.image = image;
        this.state = state;
        this.address = address;
        this.option_description = option_description;
        this.cost = cost;
        this.shipped_date = shipped_date;
        this.created_date = created_date;
    }
}
