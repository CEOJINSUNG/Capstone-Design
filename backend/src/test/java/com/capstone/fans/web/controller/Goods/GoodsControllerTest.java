package com.capstone.fans.web.controller.Goods;

import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.GoodsRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.service.GoodsService;
import com.capstone.fans.service.UserService;
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
    public void saveOrderTest() {

    }
    @Test
    public void updateOrderTest() {

    }
    @Test
    public void getOrderListTest() {

    }
    @Test
    public void updateOrderStateTest() {


    }
}
