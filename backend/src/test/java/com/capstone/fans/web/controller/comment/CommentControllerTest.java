package com.capstone.fans.web.controller.comment;

import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.comment.CommentRepository;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.UserRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.service.UserService;
import com.capstone.fans.web.dto.comment.CommentSaveDto;
import com.capstone.fans.web.dto.comment.CommentUpdateDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private FansRepository fansRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


    @Before
    public void setup() {
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


        fansRepository.save(FanS.builder()
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
    public void cleanup(){
        commentRepository.deleteAll();
        postRepository.deleteAll();
        fansRepository.deleteAll();
        clubRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void CommentSaveTest() throws Exception {
        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";

        userRepository.findAll();

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

        FanS fanS = FanS.builder()
                .address(adress)
                .profile_description(description)
                .profile_image(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build();
        fansRepository.save(fanS);
        fanS = fansRepository.findAll().get(0);

        String postType = "free";
        String content = "hellokljlkjkl";
        String title = "title:1";

        postRepository.save(Post.builder()
                .postType(postType)
                .title(title)
                .content(content)
                .user(fanS)
                .club(club)
                .image(new ArrayList<>())
                .build()
        );
        Post post = postRepository.findAll().get(0);
        Long post_id = post.getId();

        CommentSaveDto commentSaveDto = CommentSaveDto
                .builder()
                .comment("comment")
                .build();

        String url = "http://localhost:" + port + "/comment/save/" + post_id;

        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        mvc.perform(post(url)
                .sessionAttr(TOKEN_ATTR_NAME,csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(commentSaveDto)))
                .andExpect(status().isOk());

        List<Comment> all = commentRepository.findByPost(post);

        assertThat(all.get(0).getComment()).isEqualTo("comment");

    }
    @Test
    @WithUserDetails(value = "email@asdf", userDetailsServiceBeanName = "userService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void CommentUpdateTest() throws Exception {
        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";

        userRepository.findAll();

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

        FanS fanS = FanS.builder()
                .address(adress)
                .profile_description(description)
                .profile_image(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build();
        fansRepository.save(fanS);
        fanS = fansRepository.findAll().get(0);

        String postType = "free";
        String content = "hellokljlkjkl";
        String title = "title:1";

        postRepository.save(Post.builder()
                .postType(postType)
                .title(title)
                .content(content)
                .user(fanS)
                .club(club)
                .image(new ArrayList<>())
                .build()
        );
        Post post = postRepository.findAll().get(0);
        // Long post_id = post.getId();
        commentRepository.save(Comment.builder()
                .comment(content)
                .post(post)
                .user(club)
                .build()
        );
        Comment comment = commentRepository.findAll().get(0);
        Long comment_id = comment.getId();
        String updated_comment = "comment2";

        CommentUpdateDto commentUpdateDto = CommentUpdateDto.builder().conmment(updated_comment).build();
        String url = "http://localhost:" + port + "/comment/update/" + comment_id;


        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        MvcResult mvcResult = mvc.perform(put(url)
                .sessionAttr(TOKEN_ATTR_NAME,csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(commentUpdateDto)))
                .andExpect(status().isOk())
                .andReturn();

        List<Comment> all = commentRepository.findAll();

        Long id = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Long.class);
        assertThat(id).isEqualTo(-2L);
        // assertThat(all.get(0).getComment()).isEqualTo(updated_comment);

    }

}
