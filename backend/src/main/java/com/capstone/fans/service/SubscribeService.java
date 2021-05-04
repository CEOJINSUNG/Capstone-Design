package com.capstone.fans.service;


import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.membership.MembershipRepository;
import com.capstone.fans.domain.subscribe.Subscribe;
import com.capstone.fans.domain.subscribe.SubscribeRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.erorrs.ErrorCodes;
import com.capstone.fans.web.dto.subscribe.SubscribeListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final MembershipRepository membershipRepository;
    private final SubscribeRepository subscribeRepository;
    private final MembershipService membershipService;



    @Transactional
    public Long  saveSubscribe(Long membership_id, User user){
        Membership membership = membershipRepository.getOne(membership_id);
        if(membership == null)
            return ErrorCodes.NOT_EXIST;

        return subscribeRepository.save(Subscribe.builder()
                .membership(membership)
                .user(user)
                .total_cash(0L)
                .payment_date(LocalDateTime.now())
                .build()
        ).getId();
    }

    @Transactional
    public Long  cancelSubscribe(Long subscribe_id, User user){
        Subscribe subscribe = subscribeRepository.getOne(subscribe_id);

        if(subscribe == null)
            return ErrorCodes.NOT_EXIST;
        else if(!user.getId().equals(subscribe.getUser().getId()))
            return ErrorCodes.NOT_SAME_USER;

        subscribeRepository.delete(subscribe);

        return subscribe_id;
    }

    @Transactional(readOnly = true)
    public List<SubscribeListDto> getSubscribeList(User user){
        List<Subscribe> subscribes =  subscribeRepository.findByUser(user);
        return subscribes.stream().map(s -> SubscribeListDto.builder()
                .membershipResponseDto(membershipService.findById(s.getMembership().getId()))
                .total_cash(s.getTotal_cash())
                .payment_date(s.getPayment_date())
                .build()).collect(Collectors.toList());
    }


    @Transactional
    public void processPayment() {
        List<Subscribe> subscribes = subscribeRepository.findByDateLimit(LocalDateTime.now());
        for (Subscribe subscribe : subscribes) {
            if (false/* 돈이 모자를 경우 */) {
                /*모자를 시 알고리즘*/
            } else {
                /* 블록체인 송금 알고리즘*/
                subscribe.updatePayment();
            }
        }
    }
}
