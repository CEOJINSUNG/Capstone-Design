package com.capstone.fans.web.controller.goods;


import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.GoodsService;
import com.capstone.fans.web.dto.goods.GoodsOrderSaveDto;
import com.capstone.fans.web.dto.goods.GoodsSaveDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class GoodsController {
    private final GoodsService goodsService;


    @PostMapping("/goods")
    public Long saveGoods(@RequestBody GoodsSaveDto goodsSaveDto, @AuthenticationPrincipal User user){
        return goodsService.save(goodsSaveDto, user);
    }

    @PostMapping("/goods/buy/{id}")
    public Long saveOrder(@RequestBody GoodsOrderSaveDto goodsOrderSaveDto, @AuthenticationPrincipal User user){
        return goodsService.saveOrder(goodsOrderSaveDto, user);
    }



}
