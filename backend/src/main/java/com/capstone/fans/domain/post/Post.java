package com.capstone.fans.domain.post;


import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.fans.FanS;
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
    private FanS fanS;

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
    public Post(FanS fanS, Club club, String postType, String title, String content, List<byte[]> image) {
        this.fanS = fanS;
        this.club = club;
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
