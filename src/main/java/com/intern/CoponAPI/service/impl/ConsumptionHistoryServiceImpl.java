package com.intern.CoponAPI.service.impl;

import com.intern.CoponAPI.model.ConsumptionRequest;
import com.intern.CoponAPI.repository.ConsumptionHistoryRepository;
import com.intern.CoponAPI.repository.CouponRepository;
import com.intern.CoponAPI.entity.ConsumptionHistory;
import com.intern.CoponAPI.entity.Coupon;
import com.intern.CoponAPI.model.enums.DiscountType;
import com.intern.CoponAPI.service.ConsumptionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsumptionHistoryServiceImpl implements ConsumptionHistoryService {
    private final CouponRepository couponRepository;
    private final ConsumptionHistoryRepository consumptionHistoryRepository;
    @Override
    public List<ConsumptionHistory> getCouponConsumptionHistories(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        List<ConsumptionHistory> consumptionHistories = coupon.getConsumptionHistories();
        return consumptionHistories;
    }

    @Override
    public Double consumeCoupon(ConsumptionRequest consumptionRequest) {
        Coupon coupon = couponRepository.findByCode(consumptionRequest.getCoupon_code()).orElseThrow(() -> new RuntimeException("not found"));
        boolean isValidCoupon = isValidCoupon(consumptionRequest.getCoupon_code());
        if(!isValidCoupon){
            throw new RuntimeException("Not valid any more");
        }
        double amount = consumptionRequest.getAmount();
        DiscountType discount_type = coupon.getDiscount_type();
        Integer discount_value = coupon.getDiscount_value();
        double amountAfterDiscount = calcAmountAfterDiscount(amount, discount_type , discount_value);
        Integer remainingCount = coupon.getRemaining_count();
        coupon.setRemaining_count(remainingCount -1);
        couponRepository.save(coupon);
        addToConsumptionHistory(coupon, consumptionRequest);
        return amountAfterDiscount;
    }

    private void addToConsumptionHistory(Coupon coupon, ConsumptionRequest consumptionRequest) {
        ConsumptionHistory consumptionHistory = new ConsumptionHistory();
        consumptionHistory.setConsumption_date(LocalDateTime.now());
        consumptionHistory.setCoupon(coupon);
        consumptionHistory.setCustomer_email(consumptionRequest.getCustomer_email());
        consumptionHistory.setOrder_id(consumptionRequest.getOrder_id());
        consumptionHistory.setDiscount_value(coupon.getDiscount_value());
        consumptionHistoryRepository.save(consumptionHistory);
    }

    private boolean isValidCoupon(String couponCode) {
        Coupon coupon = couponRepository.findByCode(couponCode).orElseThrow(() -> new RuntimeException("no more cou"));
        if (coupon.getRemaining_count() < 1){
            return false;
        }
        return true;
    }

    private double calcAmountAfterDiscount(double amount, DiscountType type,double discount_value ){
        double amountAfterDiscount = 0;
        if(type == DiscountType.FIXED){
            amountAfterDiscount = amount - discount_value;
        }else if (type == DiscountType.PERCENTAGE){
            double actualDiscountValue = (amount * discount_value)/100;
            amountAfterDiscount = amount - actualDiscountValue;
        }
        return amountAfterDiscount;
    }
}
