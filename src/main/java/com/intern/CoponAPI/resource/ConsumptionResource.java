package com.intern.CoponAPI.resource;

import com.intern.CoponAPI.model.ConsumptionRequest;
import com.intern.CoponAPI.entity.ConsumptionHistory;
import com.intern.CoponAPI.service.ConsumptionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("coupon")
public class ConsumptionResource {
    private final ConsumptionHistoryService consumptionHistoryService;
    @GetMapping("consumption-history/{couponId}")
    public List<ConsumptionHistory> getCouponConsumptionHistories(@PathVariable Long couponId){
        return consumptionHistoryService.getCouponConsumptionHistories(couponId);
    }

    @PostMapping("consume")
    public Double consumeCoupon (@RequestBody ConsumptionRequest consumptionRequest){
        return consumptionHistoryService.consumeCoupon(consumptionRequest);
    }
}
