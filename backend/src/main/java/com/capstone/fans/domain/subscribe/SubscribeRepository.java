package com.capstone.fans.domain.subscribe;

import com.capstone.fans.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Query("select s from Subscribe s where s.payment_date < :limit_time")
    List<Subscribe> findByDateLimit(@Param("limit_time") LocalDateTime limit_time);


    List<Subscribe> findByUser(User user);
}
