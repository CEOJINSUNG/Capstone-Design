package com.capstone.fans.web.dto.goods;

import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.Club;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class GoodsSaveDto {
    private String name;
    private String type;
    private String description;
    private Long price;
    private List<byte[]> pictures;
    private Long stock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Option> options;


    @Builder
    public GoodsSaveDto(String name, String type, String description, Long price, List<byte[]> pictures, Long stock, LocalDateTime startDate, LocalDateTime endDate, List<Option> options) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.pictures = pictures;
        this.stock = stock;
        this.startDate = startDate;
        this.endDate = endDate;
        this.options = options;
    }

    public Goods toEntity(Club club){
        return Goods.builder()
                .club(club)
                .name(name)
                .type(type)
                .description(description)
                .price(price)
                .pictures(pictures)
                .stock(stock)
                .startDate(startDate)
                .endDate(endDate)
                .options(options)
                .build();
    }

}
