package com.capstone.fans.service;


import com.capstone.fans.domain.goods.Goods;
import com.capstone.fans.domain.goods.GoodsRepository;
import com.capstone.fans.domain.goods.option.Option;
import com.capstone.fans.domain.goods.option.OptionRepository;
import com.capstone.fans.domain.goods_order.GoodsOrder;
import com.capstone.fans.domain.goods_order.GoodsOrderRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.Club;

import com.capstone.fans.erorrs.ErrorCodes;
import com.capstone.fans.web.dto.goods.GoodsOrderSaveDto;
import com.capstone.fans.web.dto.goods.GoodsSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsOrderRepository goodsOrderRepository;
    private final OptionRepository optionRepository;

    public static final String initialState = "상품 준비중";


    @Transactional
    public Long save(GoodsSaveDto goodsSaveDto, User user){
        if(!(user instanceof Club))
            return -3L;
        return goodsRepository.save(goodsSaveDto.toEntity((Club)user)).getId();
    }


    @Transactional
    public Long saveOrder(GoodsOrderSaveDto goodsOrderSaveDto, User user) {
        Goods goods = goodsRepository.findById(goodsOrderSaveDto.getGoods_id()).orElse(null);
        Option option = optionRepository.findById(goodsOrderSaveDto.getOption_id()).orElse(null);

        if(goods == null || option == null)
            return ErrorCodes.NOT_EXIST;


        /*
        Block Chain Logic. 잔액 부족시 ErrorCodes.NO_MONEY 반환
        */


        return goodsOrderRepository.save(
                GoodsOrder.builder()
                .user(user)
                .goods(goods)
                .state(initialState)
                .address(goodsOrderSaveDto.getAdress())
                .shipped_date(null)
                .option(option)
                .build()).getId();
    }


    @Transactional
    public Long updateOrder(){

        /*추가결제 진행 또는 환불 잔액부족시 에러*/










        return -1L;
    }



}
