package com.capstone.fans.domain.comment;

import com.capstone.fans.domain.BaseTimeEntity;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;


    @ManyToOne
    @JoinColumn
    private Post post;

    private String comment;


    @Builder
    public Comment(User user, Post post, String comment) {
        this.user = user;
        this.post = post;
        this.comment = comment;
    }


    public void update(String updated_comment){
        this.comment = updated_comment;
    }
}
