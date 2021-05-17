package com.capstone.fans.domain.user.club;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClubRepositoryTest {
    @Autowired
    ClubRepository clubRepository;

    @After
    public void cleanup(){
        clubRepository.deleteAll();
    }

    @Test
    public void 클럽저장_불러오기(){
        String adress = "suwon";
        String description = "no description";
        String clubname = "club 1";
        String image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";


        clubRepository.save(Club.builder()
                .address(adress)
                .club_description(description)
                .club_name(clubname)
                .club_picture(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .auth("USER")
                .build()
        );

        List<Club> clubList = clubRepository.findAll();


        Club club = clubList.get(0);


        assertThat(club.getAddress()).isEqualTo(adress);
        assertThat(club.getClub_description()).isEqualTo(description);
        assertThat(club.getClub_name()).isEqualTo(clubname);
        assertThat(club.getClub_picture()).isEqualTo(image);
        assertThat(club.getBlockchain_address()).isEqualTo(blockChain);
        assertThat(club.getEmail()).isEqualTo(email);
        assertThat(club.getPassword()).isEqualTo(password);
        assertThat(club.getPhone_number()).isEqualTo(phone_number);
        assertThat(club.getName()).isEqualTo(name);

    }
}
