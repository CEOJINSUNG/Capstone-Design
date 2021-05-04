package com.capstone.fans.component;

import com.capstone.fans.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@RequiredArgsConstructor
@Component
public class FanSScheduler {
    private final SubscribeService subscribeService;

    @Scheduled(cron = "10 * * * * *")
    public void test(){
        System.out.println("test");
    }

    @Scheduled(cron = "0 10 * * * *")
    public void RegularPayment(){
        subscribeService.processPayment();
    }
}
