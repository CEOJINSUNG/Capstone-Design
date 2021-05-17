package com.capstone.fans.web.dto.goods;

import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.Club;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GoodsSaveClientDto {
    private String name;
    private String type;
    private String description;
    private Long price;
    private List<String> pictures;
    private Long stock;

    private String startDate;
    private String endDate;

    private List<Option> options;


    @Builder
    public GoodsSaveClientDto(String name, String type, String description, Long price, List<String> pictures, Long stock, String startDate, String endDate, List<Option> options) {
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
}