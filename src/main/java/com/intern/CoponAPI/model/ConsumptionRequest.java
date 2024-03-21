package com.intern.CoponAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionRequest {
    private Long order_id;
    private double amount;
    private String customer_email;
    private String coupon_code;
}
