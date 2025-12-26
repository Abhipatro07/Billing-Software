package com.example.BillingSoftware.controller;

import com.example.BillingSoftware.io.*;
import com.example.BillingSoftware.service.orderService;
import com.example.BillingSoftware.service.razorpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class paymentController {

    private final razorpayService razorpayService;
    private final orderService orderService;

    // 🔹 STEP 1: Create Dummy Razorpay Order
    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public razorpayOrderResponse createRazorpayOrder(
            @RequestBody paymentRequest request) {

        return razorpayService.createOrder(
                request.getAmount(),
                request.getCurrency()
        );
    }

    // 🔹 STEP 2: Verify payment & update order
    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public orderResponse verifyPayment(
            @RequestBody paymentVarificationRequest request) {

        return orderService.verifyPayment(request);
    }
}
