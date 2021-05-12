package com.capstone.fans.domain.Subscribe;


import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.membership.MembershipRepository;
import com.capstone.fans.domain.subscribe.Subscribe;
import com.capstone.fans.domain.subscribe.SubscribeRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FanS;
import com.capstone.fans.domain.user.fans.FansRepository;
import org.junit.After;
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
public class SubscribeRepositoryTest {

    @Autowired
    FansRepository fansRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    SubscribeRepository subscribeRepository;

    @After
    public void cleanup(){
        subscribeRepository.deleteAll();
        membershipRepository.deleteAll();
        clubRepository.deleteAll();
        fansRepository.deleteAll();
    }

    @Test
    public void SubscribeSaveTest() throws Exception {
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

        FanS fanS = FanS.builder()
                .address(address)
                .profile_description(description)
                .profile_image(image)
                .email(email)
                .password(password)
                .blockchain_address(blockChain)
                .phone_number(phone_number)
                .name(name)
                .build();
        fansRepository.save(fanS);
        fanS = fansRepository.findAll().get(0);

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

        subscribeRepository.save(Subscribe.builder()
                .total_cash(100000L)
                .membership(test_membership)
                .user(fanS)
                .payment_date(DateTime)
                .build());

        Subscribe test_subscribe = subscribeRepository.findAll().get(0);

        assertThat(test_subscribe.getMembership().getId()).isEqualTo(test_membership.getId());
    }
}
