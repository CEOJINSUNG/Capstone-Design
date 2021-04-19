package com.capstone.fans.web.dto.auth;

import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.fans.FanS;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String address;
    private String phone_number;
    private String blockChain_address;
    private String user_type;

    @Builder
    public SignUpDto(String email, String password, String nickname, String name, String address, String phone_number, String blockChain_address, String user_type) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.blockChain_address = blockChain_address;
        this.user_type = user_type;
    }

    public FanS toFanSEntity(){
        return FanS.builder()
                .profile_description("축구에 관심이 많은 회원입니다.")
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .address(address)
                .phone_number(phone_number)
                .blockchain_address(blockChain_address)
                .auth("NORMAL")
                .build();
    }

    public Club toClubEntity(){
        return Club.builder()
                .club_name("club0")
                .club_description("축구팀입니다.")
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .address(address)
                .phone_number(phone_number)
                .blockchain_address(blockChain_address)
                .auth("NORMAL")
                .build();
    }
}
