package com.example.BillingSoftware.service;

import com.example.BillingSoftware.io.razorpayOrderResponse;

public interface razorpayService {

    razorpayOrderResponse createOrder(Double amount, String currency);

}
