package com.intern.CoponAPI.service;

import com.intern.CoponAPI.entity.Coupon;

import java.util.List;

public interface CouponService {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> viewAllCoupons();
}
