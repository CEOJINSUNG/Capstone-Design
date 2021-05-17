package com.capstone.fans.web.dto.post;


import com.capstone.fans.web.dto.user.SimpleUserInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
public class PostListDto {
    private Long id;
    private String title;
    private String simpleContent;
    private String image;
    private SimpleUserInfoDto simpleUserInfoDto;

    @Builder

    public PostListDto(Long id, String title, String simpleContent, String image, SimpleUserInfoDto simpleUserInfoDto) {
        this.id = id;
        this.title = title;
        this.simpleContent = simpleContent;
        this.image = image;
        this.simpleUserInfoDto = simpleUserInfoDto;
    }
}
