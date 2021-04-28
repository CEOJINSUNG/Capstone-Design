package com.capstone.fans.domain.subscribe;

import com.capstone.fans.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    List<Subscribe> findByUser(User user);
}
