package com.example.BillingSoftware.service.impl;

import com.example.BillingSoftware.io.razorpayOrderResponse;
import com.example.BillingSoftware.service.razorpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class razorpayServiceImpl implements razorpayService {

    @Override
    public razorpayOrderResponse createOrder(Double amount, String currency) {

        // Simulate payment gateway delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}

        long currentTime = System.currentTimeMillis();

        return razorpayOrderResponse.builder()
                .id("order" + currentTime)
                .entity("order")
                .amount((int) (amount * 100))
                .currency(currency)
                .status("created")
                .receipt("receipt_" + currentTime)
                .createdAt(new Date())
                .build();
    }
}
