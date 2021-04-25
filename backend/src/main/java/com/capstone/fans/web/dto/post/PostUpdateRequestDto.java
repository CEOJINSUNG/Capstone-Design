package com.capstone.fans.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private String postType;
    private List<byte[]> images;

    @Builder
    public PostUpdateRequestDto(String title, String content, String postType, List<byte[]> images){
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.images = images;
    }
}
