package com.capstone.fans.domain.goods_order;

import com.capstone.fans.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsOrderRepository extends JpaRepository<GoodsOrder, Long> {
    Page<GoodsOrder> findByUser(User user, Pageable pageable);
}
