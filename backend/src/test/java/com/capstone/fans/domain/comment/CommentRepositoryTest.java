package com.capstone.fans.domain.comment;


import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.UserRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

// Test success
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    FansRepository fansRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    UserRepository userRepository;


    @After
    public void cleanup(){
        commentRepository.deleteAll();
        postRepository.deleteAll();
        fansRepository.deleteAll();
        clubRepository.deleteAll();
    }

    @Test
    public void 댓글저장_불러오기(){
        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        String image = null;
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



        commentRepository.save(Comment.builder()
                .comment(content)
                .post(post)
                .user(club)
                .build()
        );
        Comment comment = commentRepository.findAll().get(0);




        assertThat(comment.getComment()).isEqualTo(content);
        assertThat(comment.getUser() instanceof Club).isEqualTo(true);
        assertThat(((Club)comment.getUser()).getClub_name()).isEqualTo(club.getClub_name());
    }
}
