package com.capstone.fans.web.controller.post;


import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.UserRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FanSRepositoryTest;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.post.PostSaveRequestDto;
import com.capstone.fans.web.dto.post.PostUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private FansRepository fanSRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    private MockMvc mvc;


    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        String adress = "suwon";
        String description = "no description";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell1";


        fanSRepository.save(FanS.builder()
                .address(adress)
                .profile_description(description)
                .profile_image(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .auth("USER")
                .build()
        );
    }

    @After
    public void tearDown() throws Exception{
        postRepository.deleteAll();
        clubRepository.deleteAll();
        fanSRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void PostSaveTest() throws Exception{


        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";


        Long clubId = clubRepository.save(Club.builder()
                .address(adress)
                .club_description(description)
                .club_name(clubname)
                .club_picture(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build()
        ).getId();



        String title = "title";
        String content = "content";
        List<byte[]> images = new ArrayList<>();
        //byte[] temp = 1;
        String category = "category";
        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .title(title)
                .contents(content)
                .images(images)
                .category("free")
                .build();

        String url = "http://localhost:" + port + "/post/save/" + clubId;

        // when
        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        mvc.perform(post(url)
                .sessionAttr(TOKEN_ATTR_NAME,csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postSaveRequestDto)))
                .andExpect(status().isOk());


        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }


    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void PostUpdateTest() throws Exception {
        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asfdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";


        Long clubId = clubRepository.save(Club.builder()
                .address(adress)
                .club_description(description)
                .club_name(clubname)
                .club_picture(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build()
        ).getId();

        List<byte[]> images = new ArrayList<>();
        String PostType = "postType";

        Post savedPost = postRepository.save(
                Post.builder()
                        .postType("postType")
                        .title("title")
                        .content("content")
                        .image(images)
                        .user(clubRepository.getOne(clubId))
                        .club(clubRepository.getOne(clubId))
                        .image(new ArrayList<>())
                        .build());

        Long updatedId = savedPost.getId();
        String updated_title = "title2";
        String updated_content = "content2";

        PostUpdateRequestDto postUpdateRequestDto = PostUpdateRequestDto.builder()
                .title(updated_title)
                .content(updated_content)
                .postType(PostType)
                .images(images)
                .build();

        String url = "http://localhost:" + port + "/post/update/" + updatedId;

        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        MvcResult mvcResult = mvc.perform(put(url)
                .sessionAttr(TOKEN_ATTR_NAME,csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postUpdateRequestDto)))
                .andExpect(status().isOk())
                .andReturn();


        List<Post> all = postRepository.findAll();

        Long id = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Long.class);
        assertThat(id).isEqualTo(-2L);
    }

}
