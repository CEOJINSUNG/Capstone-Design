package com.capstone.fans.web.dto.comment;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.web.dto.user.SimpleUserInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Long comments_id;
    private String comments;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private SimpleUserInfoDto user;

    @Builder
    public CommentDto(Long comments_id, String comments, LocalDateTime createdDate, LocalDateTime modifiedDate, SimpleUserInfoDto user) {
        this.comments_id = comments_id;
        this.comments = comments;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.user = user;
    }

    public static CommentDto createCommentDto(Comment comment){
        return CommentDto.builder()
                .comments_id(comment.getId())
                .comments(comment.getComment())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .user(SimpleUserInfoDto.builder()
                        .id(comment.getUser().getId())
                        .nickname(comment.getUser().getNickname())
                        .profile(comment.getUser().getProfile_image())
                        .build())
                .build();
    }
}
