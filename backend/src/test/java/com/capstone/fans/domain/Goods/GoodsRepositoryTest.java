package com.capstone.fans.domain.Goods;

import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.GoodsRepository;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.goods.option.OptionRepository;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {
    @Autowired
    ClubRepository clubRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    OptionRepository optionRepository;

    @After
    public void cleanup(){
        optionRepository.deleteAll();
        goodsRepository.deleteAll();
        clubRepository.deleteAll();
    }

    @Test
    public void GoodsSaveLoadTest() {
        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        String image = null;
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

        assertThat(goods.getDescription()).isEqualTo(GoodsDescription);
        assertThat(goods.getPrice()).isEqualTo(GoodsPrice);
        assertThat(goods.getName()).isEqualTo(GoodsName);
        assertThat((goods.getClub()).getClub_description()).isEqualTo("no description");
    }

    @Test
    public void GoodsAddOptionTest() {
        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        String image = null;
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


        String OptionName = "option_name";
        Long OptionCost = 1L;

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
                .options(new ArrayList<>())
                .build());

        List<Goods> goodsList = goodsRepository.findAll();

        optionRepository.save(Option.builder()
                .goods(goodsList.get(0))
                .name(OptionName)
                .costs(OptionCost)
                .build()
        );

        Goods goods = goodsList.get(0);

        assertThat(goods.getDescription()).isEqualTo(GoodsDescription);
        assertThat(goods.getPrice()).isEqualTo(GoodsPrice);
        assertThat(goods.getName()).isEqualTo(GoodsName);
        assertThat((goods.getClub()).getClub_description()).isEqualTo("no description");


        goods = goodsRepository.findAll().get(0);

        Option option = goods.getOptions().get(0);
        System.out.println(option.getGoods().getId());
        Option option2 = optionRepository.findAll().get(0);
        System.out.println(option2.getGoods().getId());

        assertThat(option.getName()).isEqualTo(OptionName);
        assertThat(option.getCosts()).isEqualTo(OptionCost);
    }
}