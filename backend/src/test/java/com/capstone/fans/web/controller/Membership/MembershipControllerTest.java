package com.capstone.fans.web.controller.Membership;

import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.membership.MembershipRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.erorrs.ErrorCodes;
import com.capstone.fans.service.MembershipService;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.membership.MembershipSaveClientDto;
import com.capstone.fans.web.dto.membership.MembershipSaveDto;
import com.capstone.fans.web.dto.membership.MembershipUpdateClientDto;
import com.capstone.fans.web.dto.membership.MembershipUpdateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MembershipControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FansRepository fansRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private MembershipService membershipService;

    private MockMvc mockMvc;
    private Long club_id;
    private Long fans_userId;

    @After
    public void teardown(){
        membershipRepository.deleteAll();
        clubRepository.deleteAll();
        fansRepository.deleteAll();
    }


    @Before
    public void setup() {
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

        fans_userId = fansRepository.save(FanS.builder()
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
    }

    @Test
    @WithUserDetails(value = "club@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void MembershipSaveTest() throws Exception {

        byte[] image = null;

        LocalDateTime DateTime = LocalDateTime.now();

        MembershipSaveClientDto membershipSaveDto = MembershipSaveClientDto.builder()
                .image(image)
                .membershipName("test_membership")
                .description("test_desc")
                .cashPerMonth(100L)
                .valid_date(DateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .build();

        String url = "http://localhost:" + port + "/membership/save";

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(membershipSaveDto)))
                .andDo(print())
                .andExpect(status().isOk());

        Membership test_membership = membershipRepository.findAll().get(0);

        assertThat(test_membership.getMembershipName()).isEqualTo("test_membership");
        assertThat(test_membership.getDescription()).isEqualTo("test_desc");
    }

    @Test
    @WithUserDetails(value = "club@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void MembershipUpdateTest() throws Exception{
        byte[] image = null;
        Club club = clubRepository.findAll().get(0);
        LocalDateTime DateTime = LocalDateTime.now();

        membershipRepository.save(Membership.builder()
                .club(club)
                .membershipImage(image)
                .cashPerMonth(100L)
                .membershipName("test_membership")
                .description("desc")
                .valid_date(DateTime)
                .build());

        List<Membership> all = membershipRepository.findAll();
        Membership test_membership = all.get(0);

        MembershipUpdateClientDto membershipUpdateDto = MembershipUpdateClientDto.builder()
                .membershipImage(image)
                .membershipName("aaa")
                .description("ddd")
                .valid_date(DateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .cashPerMonth(100L)
                .build();

        String url = "http://localhost:" + port + "/membership/update/" + test_membership.getId();

        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(membershipUpdateDto)))
                .andDo(print())
                .andExpect(status().isOk());


        Membership updated_membership = membershipRepository.findAll().get(0);

        assertThat(updated_membership.getMembershipName()).isEqualTo("aaa");
        assertThat(updated_membership.getDescription()).isEqualTo("ddd");
    }

    @Test
    @WithUserDetails(value = "club@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void MembershipDeleteTest() throws Exception {
        byte[] image = null;
        Club club = clubRepository.findAll().get(0);
        LocalDateTime DateTime = LocalDateTime.now();

        membershipRepository.save(Membership.builder()
                .club(club)
                .membershipImage(image)
                .cashPerMonth(100L)
                .membershipName("test_membership")
                .description("desc")
                .valid_date(DateTime)
                .build());

        List<Membership> all = membershipRepository.findAll();
        Membership test_membership = all.get(0);

        String url = "http://localhost:" + port + "/membership/delete/" + test_membership.getId();

        mockMvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        System.out.println(membershipRepository.findAll().size());
    }
}
