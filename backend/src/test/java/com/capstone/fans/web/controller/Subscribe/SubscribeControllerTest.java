package com.capstone.fans.web.controller.Subscribe;

import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.membership.MembershipRepository;
import com.capstone.fans.domain.subscribe.Subscribe;
import com.capstone.fans.domain.subscribe.SubscribeRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.subscribe.SubscribeSaveClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscribeControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FansRepository fansRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;
    private Long club_id;
    private Long fans_id;


    @Before
    public void setup () {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        String address = "suwon";
        String description = "no description";
        String clubname = "club 1";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "asdf@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";

        fans_id = fansRepository.save(FanS.builder()
                .address(address)
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

        String club_email = "club@asdf";
        clubRepository.save(Club.builder()
                .address(address)
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
                .build());

        club_id = clubRepository.findAll().get(0).getId();

        LocalDateTime DateTime = LocalDateTime.now();

        Club club = clubRepository.findAll().get(0);
        membershipRepository.save(Membership.builder()
                .club(club)
                .membershipImage(image)
                .cashPerMonth(100L)
                .membershipName("test_membership")
                .description("desc")
                .valid_date(DateTime)
                .build());
    }

    @Test
    @WithUserDetails(value = "asdf@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void SubscribeSaveTest() throws Exception {
        byte[] image = null;
        LocalDateTime DateTime = LocalDateTime.now();

        List<Membership> all = membershipRepository.findAll();
        Membership test_membership = all.get(0);
        Long membership_id = test_membership.getId();

        SubscribeSaveClientDto subscribeSaveDto = SubscribeSaveClientDto.builder()
                .payment_day(DateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .total_cash(100000L)
                .build();

        String url = "http://localhost:" + port + "/subscribe/save/" + membership_id;

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(subscribeSaveDto)))
                .andDo(print())
                .andExpect(status().isOk());

        Subscribe subscribe = subscribeRepository.findAll().get(0);

        assertThat(subscribe.getMembership().getId()).isEqualTo(test_membership.getId());
        assertThat(subscribe.getUser().getId()).isEqualTo(fans_id);
    }

    @Test
    @WithUserDetails(value = "asdf@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void CancelSubscribeTest() throws Exception {

        LocalDateTime DateTime = LocalDateTime.now();

        List<Membership> all = membershipRepository.findAll();
        Membership test_membership = all.get(0);
        Long membership_id = test_membership.getId();

        subscribeRepository.save(Subscribe.builder()
                .total_cash(100000L)
                .membership(test_membership)
                .user(fansRepository.findAll().get(0))
                .payment_date(DateTime)
                .build());

        Subscribe test_subscribe = subscribeRepository.findAll().get(0);

        String url = "http://localhost:" + port + "/subscribe/delete/" + membership_id;

        mockMvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        System.out.println(subscribeRepository.findAll().size());
    }
}
