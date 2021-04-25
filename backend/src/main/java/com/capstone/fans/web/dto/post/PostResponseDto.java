package com.capstone.fans.web.dto.post;


import com.capstone.fans.web.dto.comment.CommentDto;
import com.capstone.fans.web.dto.user.SimpleUserInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private String title;
    private List<byte[]> images;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private SimpleUserInfoDto user;
    private List<CommentDto> commentDtoList;
    private String category;


    @Builder
    public PostResponseDto(String title, List<byte[]> images, String contents, LocalDateTime createdDate, LocalDateTime modifiedDate, SimpleUserInfoDto user, List<CommentDto> commentDtoList, String category) {
        this.title = title;
        this.images = images;
        this.contents = contents;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.user = user;
        this.commentDtoList = commentDtoList;
        this.category = category;
    }
}
