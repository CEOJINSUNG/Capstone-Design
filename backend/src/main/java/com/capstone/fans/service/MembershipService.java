package com.capstone.fans.service;


import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.membership.MembershipRepository;
import com.capstone.fans.domain.subscribe.SubscribeRepository;
import com.capstone.fans.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MembershipService {
    private final MembershipRepository membershipRepository;
    private final SubscribeRepository subscribeRepository;




    @Transactional(readOnly = true)
    public Membership findById(Long id){
        return membershipRepository.findById(id).orElse(null);
    }







}
