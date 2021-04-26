package com.capstone.fans.web.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateDto {
    private String comment;

    @Builder
    public CommentUpdateDto(String conmment){
        this.comment = conmment;
    }
}
