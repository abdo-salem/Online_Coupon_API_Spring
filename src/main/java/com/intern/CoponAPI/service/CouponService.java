package com.intern.CoponAPI.service;

import com.intern.CoponAPI.entity.Coupon;
import com.intern.CoponAPI.model.dto.CouponDto;

import java.util.List;

public interface CouponService {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> viewAllCoupons();
    CouponDto getCouponById(Long couponId);
}
