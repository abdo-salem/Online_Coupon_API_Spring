package com.intern.CoponAPI.resource;

import com.intern.CoponAPI.entity.Coupon;
import com.intern.CoponAPI.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("coupon")
@RequiredArgsConstructor
public class CouponResource {

    private final CouponService couponService;

    @PostMapping("create")
    public ResponseEntity<?> createdCoupon(@RequestBody Coupon coupon){
        Coupon createdCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createdCoupon);
    }

    @GetMapping("allCoupons")
    public ResponseEntity<List<Coupon>> viewAllCoupons(){
        return ResponseEntity.ok(couponService.viewAllCoupons());
    }
}
