package com.capstone.fans.domain.user.fans;


import com.capstone.fans.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "F")
public class FanS extends User {
    private String profile_description;
    @Builder

    public FanS(String email, String password, String name, String blockchain_address, String nickname, String address, String phone_number, String profile_image, String auth, String profile_description) {
        super(email, password, name, blockchain_address, nickname, address, phone_number, profile_image, auth);
        this.profile_description = profile_description;
    }
}
