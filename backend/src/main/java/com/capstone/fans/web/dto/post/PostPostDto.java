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
public class PostPostDto {
    private String title;
    private List<byte[]> images;
    private String contents;
    private String category;


    @Builder
    public PostPostDto(String title, List<byte[]> images, String contents, String category) {
        this.title = title;
        this.images = images;
        this.contents = contents;
        this.category = category;
    }
}
