package com.example.BillingSoftware.service;

import com.example.BillingSoftware.io.orderRequest;
import com.example.BillingSoftware.io.orderResponse;
import com.example.BillingSoftware.io.paymentVarificationRequest;

import java.util.List;

public interface orderService {
    orderResponse createOrder(orderRequest request);

    void deleteOrder(String orderId);

    List<orderResponse> getLatestOrders();

    orderResponse verifyPayment(paymentVarificationRequest request);
}
