package com.capstone.fans.web.controller.auth;

import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.web.dto.auth.SignUpDto;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    FansRepository fansRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

    }

    @After
    public void tearDown() throws Exception{
        fansRepository.deleteAll();
        clubRepository.deleteAll();

    }

    @Test
    @WithMockUser(roles="USER")
    public void fansSignUpTest() throws Exception{

        SignUpDto signUpDto = SignUpDto.builder()
                .email("email")
                .password("password")
                .nickname("한글")
                .name("name")
                .address("address")
                .phone_number("1111")
                .blockChain_address("2222")
                .user_type("FANS")
                .build();

        String url = "http://localhost:" + port + "/auth/signup";

        // when
        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        mockMvc.perform(post(url)
                .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signUpDto)))
                .andExpect(status().isOk());


        // then
        List<FanS> all = fansRepository.findAll();
        assertThat(all.get(0).getEmail()).isEqualTo("email");
        assertThat(all.get(0).getName()).isEqualTo("name");
        assertThat(all.get(0).getNickname()).isEqualTo("한글");
        assertThat(all.get(0).getAddress()).isEqualTo("address");
        assertThat(all.get(0).getPhone_number()).isEqualTo("1111");
        assertThat(all.get(0).getBlockchain_address()).isEqualTo("2222");
    }


    @Test
    @WithMockUser(roles="USER")
    public void clubSignUpTest() throws Exception {
        SignUpDto signUpDto = SignUpDto.builder()
                .email("email")
                .password("password")
                .nickname("한글")
                .name("name")
                .address("address")
                .phone_number("1111")
                .blockChain_address("2222")
                .user_type("CLUB")
                .build();

        String url = "http://localhost:" + port + "/auth/signup";

        // when
        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        mockMvc.perform(post(url)
                .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(signUpDto)))
                .andExpect(status().isOk());


        // then
        List<Club> all = clubRepository.findAll();
        assertThat(all.get(0).getEmail()).isEqualTo("email");
        assertThat(all.get(0).getName()).isEqualTo("name");
        assertThat(all.get(0).getNickname()).isEqualTo("한글");
        assertThat(all.get(0).getAddress()).isEqualTo("address");
        assertThat(all.get(0).getPhone_number()).isEqualTo("1111");
        assertThat(all.get(0).getBlockchain_address()).isEqualTo("2222");

    }

}
