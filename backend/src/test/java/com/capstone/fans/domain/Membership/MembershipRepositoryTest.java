package com.capstone.fans.domain.Membership;

import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.membership.MembershipRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MembershipRepositoryTest {
    @Autowired
    ClubRepository clubRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @Test
    public void MembershipSaveTest() throws Exception {
        String address = "suwon";
        String description = "no description";
        String clubname = "club 1";
        byte[] image = null;
        String blockChain = "asdf";
        String email = "email@asdf";
        String password = "qwer!@#$";
        String phone_number = "123-123-123";
        String name = "hell0";


        Club club = Club.builder()
                .address(address)
                .club_description(description)
                .club_name(clubname)
                .club_picture(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build();
        clubRepository.save(club);
        club = clubRepository.findAll().get(0);

        LocalDateTime DateTime = LocalDateTime.now();

        membershipRepository.save(Membership.builder()
                .club(club)
                .membershipImage(image)
                .cashPerMonth(100L)
                .membershipName("test_membership")
                .description("desc")
                .valid_date(DateTime)
                .build());

        List<Membership> all = membershipRepository.findAll();
        Membership test_membership = all.get(0);


        assertThat(test_membership.getDescription()).isEqualTo("desc");
        assertThat(test_membership.getMembershipName()).isEqualTo("test_membership");
    }
}
