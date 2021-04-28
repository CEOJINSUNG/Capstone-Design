package com.capstone.fans.domain.membership;

import com.capstone.fans.domain.user.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByClub(Club club);
}
