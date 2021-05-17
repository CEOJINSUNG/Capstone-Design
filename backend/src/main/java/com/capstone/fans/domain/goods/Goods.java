package com.capstone.fans.domain.goods;

import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.user.club.Club;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
public class Goods extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Club club;

    private String name;

    private String type;

    private String description;

    private Long price;


    @Lob
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> pictures;

    private Long stock;

    private LocalDateTime startDate;

    private LocalDateTime endDate;


    @OneToMany(mappedBy = "goods", fetch = FetchType.EAGER)
    private List<Option> options;


    @Builder
    public Goods(Club club, String name, String type, String description, Long price, List<String> pictures, Long stock, LocalDateTime startDate, LocalDateTime endDate, List<Option> options) {
        this.club = club;
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
