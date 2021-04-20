package com.capstone.fans.domain.post;


import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.web.dto.post.PostPostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Club club;

    private String postType;

    private String title;

    private String content;

    @Lob
    @ElementCollection(fetch = FetchType.LAZY)
    private List<byte[]> image;


    @Builder
    public Post(User user, Club club, String postType, String title, String content, List<byte[]> image) {
        this.user = user;
        this.club = club;
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.image = image;
    }



    public void update(PostPostDto postPostDto){
        this.postType = postPostDto.getCategory();
        this.title = postPostDto.getTitle();
        this.content = postPostDto.getContents();
        this.image = postPostDto.getImages();
    }

}
