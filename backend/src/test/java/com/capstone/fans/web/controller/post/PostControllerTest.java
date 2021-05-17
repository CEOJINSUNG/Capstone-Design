package com.capstone.fans.web.controller.post;


import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.service.PostService;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.post.PostListDto;
import com.capstone.fans.web.dto.post.PostSaveRequestDto;
import com.capstone.fans.web.dto.post.PostUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.TestExecutionEvent;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @Autowired
    private PostService postService;

    private Long fans_userId;
    private Long club_userId;
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


        fans_userId = fanSRepository.save(FanS.builder()
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
        ).getId();

        String club_email = "club@asdf";

        club_userId = clubRepository.save(Club.builder()
                .address(adress)
                .club_description(description)
                .club_name("club_1")
                .club_picture(image)
                .email("club@asdf")
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build()).getId();
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
        String title = "title";
        String content = "content";
        List<byte[]> images = new ArrayList<>();

        PostSaveRequestDto postSaveRequestDto = PostSaveRequestDto.builder()
                .title(title)
                .contents(content)
                .images(images)
                .category("free")
                .build();

        String url = "http://localhost:" + port + "/post/save/" + club_userId;

        mvc.perform(post(url)
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
        List<byte[]> images = new ArrayList<>();
        String PostType = "postType";

        Post savedPost = postRepository.save(
                Post.builder()
                        .postType(PostType)
                        .title("title")
                        .content("content")
                        .image(images)
                        .user(fanSRepository.getOne(fans_userId))
                        .club(clubRepository.getOne(club_userId))
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

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(postUpdateRequestDto)))
                .andDo(print())
                .andExpect(status().isOk());

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updated_title);
        assertThat(all.get(0).getContent()).isEqualTo(updated_content);
    }

    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void PostDeleteTest() throws Exception {
        List<byte[]> images = new ArrayList<>();

        Post savedPost = postRepository.save(
                Post.builder()
                        .postType("postType")
                        .title("title")
                        .content("content")
                        .image(images)
                        .user(fanSRepository.getOne(fans_userId))
                        .club(clubRepository.getOne(club_userId))
                        .build());

        Long post_id = savedPost.getId();

        String url = "http://localhost:" + port + "/post/delete/" + post_id;

        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        System.out.println(postRepository.findAll().size());
    }

    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void GetPostListTest() throws Exception {
        List<byte[]> images = new ArrayList<>();

        Post savedPost_1 = postRepository.save(
                Post.builder()
                        .postType("postType")
                        .title("title_1")
                        .content("content")
                        .image(images)
                        .user(fanSRepository.getOne(fans_userId))
                        .club(clubRepository.getOne(club_userId))
                        .build());

        Post savedPost_2 = postRepository.save(
                Post.builder()
                        .postType("postType")
                        .title("title_2")
                        .content("content")
                        .image(images)
                        .user(fanSRepository.getOne(fans_userId))
                        .club(clubRepository.getOne(club_userId))
                        .build());

        Post savedPost_3 = postRepository.save(
                Post.builder()
                        .postType("postType")
                        .title("title_3")
                        .content("content")
                        .image(images)
                        .user(fanSRepository.getOne(fans_userId))
                        .club(clubRepository.getOne(club_userId))
                        .build());

        String url = "http://localhost:" + port + "/post/list/0/3";
        // ~/post/list/page_number/page_size

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());


        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(content().json(objectMapper.writeValueAsString(postService.getPostList(0, 3))));

    }
}
