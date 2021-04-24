package com.capstone.fans.domain.post;


import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


// Test Success
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    ClubRepository clubRepository;

    @Autowired
    FansRepository fansRepository;

    @Autowired
    PostRepository postRepository;


    @AfterEach
    public void cleanup(){
        postRepository.deleteAll();
        clubRepository.deleteAll();
        fansRepository.deleteAll();
    }

    @Test
    @Transactional
    public void 게시글저장_불러오기(){
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




        List<Post> postList = postRepository.findAll();


        Post post = postList.get(0);


        assertThat(post.getPostType()).isEqualTo(postType);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getImage().size()).isEqualTo(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(((FanS)post.getUser()).getProfile_description()).isEqualTo(fanS.getProfile_description());
    }
}
