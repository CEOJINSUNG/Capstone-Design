package com.capstone.fans.domain.Goods.Option;

import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.GoodsRepository;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.goods.option.OptionRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionRepositoryTest {
    @Autowired
    ClubRepository clubRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    OptionRepository optionRepository;

    @After
    public void cleanup() {
        optionRepository.deleteAll();
        goodsRepository.deleteAll();
        clubRepository.deleteAll();
    }

    @Test
    public void OptionSaveTest() {

        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";


        Club club = Club.builder()
                .address(adress)
                .club_description(description)
                .club_name(clubname)
                .club_picture(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build();
        clubRepository.save(club);
        club = clubRepository.findAll().get(0);

        String GoodsName = "item 1";

        String GoodsType = "type 1";

        String GoodsDescription = "desc 1";

        Long GoodsPrice = 100L;

        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.of(2021, 11, 12,12, 32,22,3333);


        goodsRepository.save(Goods.builder()
                .name(GoodsName)
                .type(GoodsType)
                .description(GoodsDescription)
                .price(GoodsPrice)
                .club(club)
                .pictures(new ArrayList<>())
                .startDate(startDateTime)
                .endDate(endDateTime)
                .stock(1L)
                .options(new ArrayList<Option>())
                .build());

        List<Goods> goodsList = goodsRepository.findAll();

        Goods goods = goodsList.get(0);
        Long goods_id = goods.getId();

        String OptionName = "option_name";
        Long OptionCost = 1L;

        optionRepository.save(Option.builder().goods_id(goods_id).name(OptionName).costs(OptionCost).build());

        List<Option> options = optionRepository.findAll();
        Option option = options.get(0);

        assertThat(option.getName()).isEqualTo(OptionName);
        assertThat(option.getCosts()).isEqualTo(OptionCost);


    }

}
