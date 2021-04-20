package com.capstone.fans.web.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentDto {
    private String comment;

    public PostCommentDto(String comment) {
        this.comment = comment;
    }
}
