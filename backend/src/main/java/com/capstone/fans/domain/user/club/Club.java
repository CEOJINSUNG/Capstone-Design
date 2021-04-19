package com.capstone.fans.domain.user.club;


import com.capstone.fans.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "C")
public class Club extends User {
    private String club_description;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] club_picture;

    private String club_name;

    @Builder
    public Club(String email, String password, String name, String blockchain_address, String nickname, String address, String phone_number, byte[] profile_image, String auth, String club_description, byte[] club_picture, String club_name) {
        super(email, password, name, blockchain_address, nickname, address, phone_number, profile_image, auth);
        this.club_description = club_description;
        this.club_picture = club_picture;
        this.club_name = club_name;
    }
}
