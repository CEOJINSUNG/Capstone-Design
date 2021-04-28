package com.capstone.fans.service;


import com.capstone.fans.domain.membership.Membership;
import com.capstone.fans.domain.membership.MembershipRepository;
import com.capstone.fans.domain.subscribe.SubscribeRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.erorrs.ErrorCodes;
import com.capstone.fans.web.dto.membership.MembershipSaveDto;
import com.capstone.fans.web.dto.membership.MembershipResponseDto;
import com.capstone.fans.web.dto.membership.MembershipUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MembershipService {
    private final MembershipRepository membershipRepository;
    private final SubscribeRepository subscribeRepository;
    private final ClubRepository clubRepository;



    @Transactional
    public Long saveMembership(MembershipSaveDto membershipSaveDto, User user){
        if(!(user instanceof Club))
            return ErrorCodes.NO_AUTHORITY;
        return membershipRepository.save(
                Membership.builder()
                .club((Club)user)
                .valid_date(membershipSaveDto.getValid_date())
                .cashPerMonth(membershipSaveDto.getCashPerMonth())
                .membershipName(membershipSaveDto.getMembershipName())
                .description(membershipSaveDto.getDescription())
                .membershipImage(membershipSaveDto.getMembershipImage())
                .build()
        ).getId();


    }

    @Transactional
    public Long updateMembership(Long membership_id,MembershipUpdateDto membershipUpdateDto, User user){
        Membership membership = membershipRepository.findById(membership_id).orElse(null);
        if(membership == null)
            return ErrorCodes.NOT_EXIST;
        else if(user.getId().equals(membership.getClub().getId()))
            return ErrorCodes.NO_AUTHORITY;
        membership.update(membershipUpdateDto);
        return membership_id;
    }

    @Transactional
    public Long deleteMembership(Long membership_id, User user){
        Membership membership = membershipRepository.findById(membership_id).orElse(null);
        if(membership == null)
            return ErrorCodes.NOT_EXIST;
        else if(user.getId().equals(membership.getClub().getId()))
            return ErrorCodes.NO_AUTHORITY;
        membershipRepository.delete(membership);
        return membership_id;
    }


    @Transactional(readOnly = true)
    public MembershipResponseDto findById(Long id){
        Membership membership = membershipRepository.findById(id).orElse(null);
        if(membership == null) return null;
        return MembershipResponseDto.builder()
                .id(membership.getId())
                .valid_date(membership.getValid_date())
                .cashPerMonth(membership.getCashPerMonth())
                .description(membership.getDescription())
                .membershipImage(membership.getMembershipImage())
                .membershipName(membership.getMembershipName())
                .build();
    }

    @Transactional(readOnly = true)
    public List<MembershipResponseDto> getMembershipList(Long club_id){
        Club club = clubRepository.findById(club_id).orElse(null);
        if(club == null)
            return null;
        List<Membership> membershipList = membershipRepository.findByClub(club);
        return membershipList.stream()
                .map(m -> MembershipResponseDto.builder()
                        .id(m.getId())
                        .valid_date(m.getValid_date())
                        .cashPerMonth(m.getCashPerMonth())
                        .description(m.getDescription())
                        .membershipName(m.getMembershipName())
                        .membershipImage(m.getMembershipImage())
                        .build()
                ).collect(Collectors.toList());
    }








}
