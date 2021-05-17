package com.capstone.fans.web.dto.user;

import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.fans.FanS;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String address;
    private String phone_number;
    private String profile_image;
    private String description;

    private String club_name;
    private String club_image;


    @Builder
    public UserInfoDto(Long id, String name, String nickname, String email, String address, String phone_number, String profile_image, String description, String club_name, String club_image) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
        this.profile_image = profile_image;
        this.description = description;
        this.club_name = club_name;
        this.club_image = club_image;
    }






    public static UserInfoDto createDto(User user){
        if(user instanceof FanS){
            FanS fanS = (FanS)user;
            return UserInfoDto.builder()
                    .name(fanS.getName())
                    .nickname(fanS.getNickname())
                    .email(fanS.getEmail())
                    .address(fanS.getAddress())
                    .phone_number(fanS.getPhone_number())
                    .profile_image(fanS.getProfile_image())
                    .description(fanS.getProfile_description())
                    .build();
        }else{
            Club club = (Club)user;
            return UserInfoDto.builder()
                    .name(club.getName())
                    .nickname(club.getNickname())
                    .email(club.getEmail())
                    .address(club.getAddress())
                    .phone_number(club.getPhone_number())
                    .profile_image(club.getProfile_image())
                    .description(club.getClub_description())
                    .club_image(club.getClub_picture())
                    .club_name(club.getClub_name())
                    .id(club.getId())
                    .build();
        }

    }
}
