package com.capstone.fans.web.dto.goods;

import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class GoodsOrderListDto {
    private Long id;
    private User user;
    private byte[] image;
    private String state;
    private String address;
    private Option option;
    private LocalDateTime shipped_date;
    private LocalDateTime created_date;

    public GoodsOrderListDto(Long id, User user, byte[] image, String state, String address, Option option, LocalDateTime shipped_date, LocalDateTime created_date) {
        this.id = id;
        this.user = user;
        this.image = image;
        this.state = state;
        this.address = address;
        this.option = option;
        this.shipped_date = shipped_date;
        this.created_date = created_date;
    }
}
