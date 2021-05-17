package com.capstone.fans.web.dto.post;

import com.capstone.fans.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private List<byte[]> images;
    private String contents;
    private String category;


    @Builder
    public PostSaveRequestDto(String title, List<byte[]> images, String contents, String category) {
        this.title = title;
        this.images = images;
        this.contents = contents;
        this.category = category;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .image(images)
                .content(contents)
                .build();
    }
}
