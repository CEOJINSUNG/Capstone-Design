package com.capstone.fans.domain.user.fans;




import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.*;




@SpringBootTest
public class FanSRepositoryTest {
    @Autowired
    FansRepository fanSRepository;

    @AfterEach
    public void cleanup(){
        fanSRepository.deleteAll();
    }

    @Test
    public void 팬저장_불러오기(){
        String adress = "suwon";
        String description = "no description";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell1";


        fanSRepository.save(FanS.builder()
                .address(adress)
                .profile_description(description)
                .profile_image(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build()
        );

        List<FanS> clubList = fanSRepository.findAll();


        FanS fanS = clubList.get(0);


        assertThat(fanS.getAddress()).isEqualTo(adress);
        assertThat(fanS.getProfile_description()).isEqualTo(description);
        assertThat(fanS.getProfile_image()).isEqualTo(image);
        assertThat(fanS.getBlockchain_address()).isEqualTo(blockChain);
        assertThat(fanS.getEmail()).isEqualTo(email);
        assertThat(fanS.getPassword()).isEqualTo(password);
        assertThat(fanS.getPhone_number()).isEqualTo(phone_number);
        assertThat(fanS.getName()).isEqualTo(name);





    }
}
