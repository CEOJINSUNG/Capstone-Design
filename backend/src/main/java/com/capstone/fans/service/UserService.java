package com.capstone.fans.service;

import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.UserRepository;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.domain.user.fans.FansRepository;
import com.capstone.fans.web.dto.auth.SignUpDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Getter
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final FansRepository fansRepository;
    private final ClubRepository clubRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    @Transactional
    public Long save(SignUpDto signUpDto){
        List<User> users = userRepository.findAll();
        String email = signUpDto.getEmail();
        for(User user : users){
            if(email.equals(user.getEmail()))
                return -1L;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        signUpDto.setPassword(encoder.encode(signUpDto.getPassword()));
        if(signUpDto.getUser_type().equals("FANS")){
            return fansRepository.save(signUpDto.toFanSEntity()).getId();
        }else{
            return clubRepository.save(signUpDto.toClubEntity()).getId();
        }
    }
}
