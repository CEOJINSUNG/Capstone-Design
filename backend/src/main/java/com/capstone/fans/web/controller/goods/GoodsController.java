package com.capstone.fans.web.controller.goods;

import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.GoodsService;
import com.capstone.fans.web.dto.goods.GoodsOrderListDto;
import com.capstone.fans.web.dto.goods.GoodsOrderSaveDto;
import com.capstone.fans.web.dto.goods.GoodsOrderUpdateDto;
import com.capstone.fans.web.dto.goods.GoodsSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GoodsController {
    private final GoodsService goodsService;


    @PostMapping("/goods/save")
    public Long saveGoods(@RequestBody GoodsSaveDto goodsSaveDto, @AuthenticationPrincipal User user){
        return goodsService.save(goodsSaveDto, user);
    }

    @PostMapping("/goods/buy/{id}")
    public Long saveOrder(@RequestBody GoodsOrderSaveDto goodsOrderSaveDto, @AuthenticationPrincipal User user){
        return goodsService.saveOrder(goodsOrderSaveDto, user);
    }

    @PutMapping("/goods/{id}")
    public Long updateOrder(@RequestBody GoodsOrderUpdateDto goodsOrderUpdateDto, @AuthenticationPrincipal User user){
        return goodsService.updateOrder(goodsOrderUpdateDto, user);
    }

    @DeleteMapping("/goods/{id}")
    public Long updateOrder(@PathVariable Long id, @AuthenticationPrincipal User user){
        return goodsService.cancelOrder(id, user);
    }

    @GetMapping("/goods/user_order/{page}/{size}")
    public List<GoodsOrderListDto> getOrderList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @AuthenticationPrincipal User user){
        return goodsService.getOrderList(user, page, size);
    }


    @PutMapping("/goods/clubs/order_manage/{id}")
    public Long updateOrderState(@PathVariable Long id, @RequestBody String state, @AuthenticationPrincipal User user){
        return goodsService.changeOrderState(state, id, user);
    }



}
