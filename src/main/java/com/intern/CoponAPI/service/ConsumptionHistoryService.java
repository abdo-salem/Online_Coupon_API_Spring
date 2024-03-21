package com.intern.CoponAPI.service;

import com.intern.CoponAPI.model.ConsumptionRequest;
import com.intern.CoponAPI.entity.ConsumptionHistory;

import java.util.List;

public interface ConsumptionHistoryService {
    List<ConsumptionHistory> getCouponConsumptionHistories(Long couponId);

    Double consumeCoupon(ConsumptionRequest consumptionRequest);
}
