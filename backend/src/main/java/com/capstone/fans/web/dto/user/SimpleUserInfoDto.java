package com.capstone.fans.web.dto.user;


import com.capstone.fans.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SimpleUserInfoDto {
    private Long id;
    private String nickname;
    private byte[] profile;


    @Builder
    public SimpleUserInfoDto(Long id, String nickname, byte[] profile) {
        this.id = id;
        this.nickname = nickname;
        this.profile = profile;
    }

    public static SimpleUserInfoDto createSimpleUserInfoDto(User user){
        return SimpleUserInfoDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profile(user.getProfile_image())
                .build();
    }

}
