package com.capstone.fans.web.controller.Goods;

import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.GoodsRepository;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.goods.option.OptionRepository;
import com.capstone.fans.domain.goods_order.GoodsOrder;
import com.capstone.fans.domain.goods_order.GoodsOrderRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.service.GoodsService;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.goods.GoodsOrderSaveDto;
import com.capstone.fans.web.dto.goods.GoodsOrderUpdateDto;
import com.capstone.fans.web.dto.goods.GoodsSaveClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoodsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private GoodsOrderRepository goodsOrderRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private FansRepository fansRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    private Long fans_userId;
    private Long club_userId;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        String adress = "suwon";
        String description = "no description";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell1";


        fans_userId = fansRepository.save(FanS.builder()
                .address(adress)
                .profile_description(description)
                .profile_image(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .auth("ROLE_USER")
                .build()
        ).getId();


        String clubname = "club 1";
        String club_email = "abcd@asdfsdf";

        club_userId = clubRepository.save(Club.builder()
                .address(adress)
                .club_description(description)
                .club_picture(image)
                .club_name(clubname)
                .profile_image(image)
                .email(club_email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .auth("ROLE_USER")
                .build()).getId();

    }
/*
    @After
    public void tearDown() throws Exception{
        goodsRepository.deleteAll();
        clubRepository.deleteAll();
        fansRepository.deleteAll();
    }

    @AfterAll
    public void cleanup() throws Exception{
        goodsRepository.deleteAll();
        clubRepository.deleteAll();
        fansRepository.deleteAll();
    }
*/
    @Test
    @WithUserDetails(value = "abcd@asdfsdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void saveGoodsTest() throws Exception {

        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.of(2021, 11, 12,12, 32,22,3333);

        GoodsSaveClientDto goodsSaveDto = GoodsSaveClientDto.builder()
                .name("goods")
                .type("Free")
                .description("desc")
                .price(100L)
                .pictures(new ArrayList<>())
                .stock(200L)
                .startDate(startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .endDate(endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .options(new ArrayList<>())
                .build();

        String url = "http://localhost:" + port + "/goods/save";


        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(goodsSaveDto)))
                .andExpect(status().isOk());


        List<Goods> all = goodsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo("goods");
        assertThat(all.get(0).getDescription()).isEqualTo("desc");
    }
    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void saveOrderTest() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.of(2021, 11, 12,12, 32,22,3333);

        goodsRepository.save(Goods.builder()
                .name("goods")
                .type("type")
                .description("desc")
                .price(11L)
                .club(clubRepository.getOne(club_userId))
                .pictures(new ArrayList<>())
                .startDate(startDateTime)
                .endDate(endDateTime)
                .build());

        String OptionName = "option_name";
        Long OptionCost = 1L;

        List<Goods> goodsList = goodsRepository.findAll();


        optionRepository.save(Option.builder()
                .goods(goodsList.get(0))
                .name(OptionName)
                .costs(OptionCost)
                .build()
        );

        Option option = goodsRepository.findAll().get(0).getOptions().get(0);

        GoodsOrderSaveDto goodsOrderSaveDto = GoodsOrderSaveDto.builder()
                .address("address")
                .option_id(option.getId())
                .goods_id(goodsList.get(0).getId())
                .build();


        System.out.println("fans user id : " + fans_userId);
        System.out.println("goods option id : " + option.getId());
        String url = "http://localhost:" + port + "/goods/buy/" + fans_userId;


        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(goodsOrderSaveDto)))
                .andExpect(status().isOk());

        List<GoodsOrder> all = goodsOrderRepository.findAll();
        assertThat(all.get(0).getAddress()).isEqualTo("address");
        assertThat(all.get(0).getOption().getName()).isEqualTo(OptionName);
    }

    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void updateOrderTest() throws Exception{
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.of(2021, 11, 12,12, 32,22,3333);

        goodsRepository.save(Goods.builder()
                .name("goods")
                .type("type")
                .description("desc")
                .price(11L)
                .club(clubRepository.getOne(club_userId))
                .pictures(new ArrayList<>())
                .startDate(startDateTime)
                .endDate(endDateTime)
                .build());

        String OptionName = "option_name";
        Long OptionCost = 1L;

        List<Goods> goodsList = goodsRepository.findAll();


        optionRepository.save(Option.builder()
                .goods(goodsList.get(0))
                .name(OptionName)
                .costs(OptionCost)
                .build()
        );

        Option option = goodsRepository.findAll().get(0).getOptions().get(0);

        goodsOrderRepository.save(GoodsOrder.builder()
                .address("address")
                .goods(goodsList.get(0))
                .option(option)
                .state("TEST_STATE")
                .shipped_date(LocalDateTime.now())
                .user(fansRepository.getOne(fans_userId))
                .build());


        GoodsOrderUpdateDto goodsOrderUpdateDto = GoodsOrderUpdateDto.builder()
                .address("123")
                .orderId(goodsList.get(0).getId())
                .optionId(option.getId())
                .build();

        String url = "http://localhost:" + port + "/goods/" + fans_userId;

        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(goodsOrderUpdateDto)))
                .andExpect(status().isOk());

        List<GoodsOrder> all = goodsOrderRepository.findAll();
        assertThat(all.get(0).getAddress()).isEqualTo("123");
    }
    @Test
    public void getOrderListTest() {

    }
    @Test
    @WithUserDetails(value = "abcd@asdfsdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void updateOrderStateTest() throws Exception{
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.of(2021, 11, 12,12, 32,22,3333);

        goodsRepository.save(Goods.builder()
                .name("goods")
                .type("type")
                .description("desc")
                .price(11L)
                .club(clubRepository.getOne(club_userId))
                .pictures(new ArrayList<>())
                .startDate(startDateTime)
                .endDate(endDateTime)
                .build());

        String OptionName = "option_name";
        Long OptionCost = 1L;

        List<Goods> goodsList = goodsRepository.findAll();


        optionRepository.save(Option.builder()
                .goods(goodsList.get(0))
                .name(OptionName)
                .costs(OptionCost)
                .build()
        );

        Option option = goodsRepository.findAll().get(0).getOptions().get(0);

        goodsOrderRepository.save(GoodsOrder.builder()
                .address("address")
                .goods(goodsList.get(0))
                .option(option)
                .state("TEST_STATE")
                .shipped_date(LocalDateTime.now())
                .user(fansRepository.getOne(fans_userId))
                .build());


        GoodsOrder goodsOrder = goodsOrderRepository.findAll().get(0);
        Long goods_order_id = goodsOrder.getId();
        String url = "http://localhost:" + port + "/goods/clubs/order_manage/" + goods_order_id;
        String updated_state = "배송완료";

        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updated_state)))
                .andExpect(status().isOk());

        List<GoodsOrder> all = goodsOrderRepository.findAll();
        assertThat(all.get(0).getState()).isEqualTo(updated_state);
    }
}