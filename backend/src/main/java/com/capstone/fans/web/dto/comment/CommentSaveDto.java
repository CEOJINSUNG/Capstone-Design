package com.capstone.fans.web.dto.comment;

import com.capstone.fans.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveDto {
    private String comment;

    @Builder
    public CommentSaveDto(String comment) {
        this.comment = comment;
    }

    public Comment toEntity() {
        return Comment.builder().comment(comment).build();
    }
    /*
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
    */

}
