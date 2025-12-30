package com.example.BillingSoftware.service.impl;

import com.example.BillingSoftware.entity.orderEntity;
import com.example.BillingSoftware.entity.orderItemEntity;
import com.example.BillingSoftware.io.*;
import com.example.BillingSoftware.repository.orderEntityRepository;
import com.example.BillingSoftware.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class orderServiceImpl implements orderService {

    @Autowired
    private orderEntityRepository orderEntityRepository;

    @Override
    public orderResponse createOrder(orderRequest request) {

        // 1️⃣ Create Order entity
        orderEntity newOrder = convertToOrderEntity(request);

        final orderEntity orderRef = newOrder;

        // 2️⃣ Set payment details
        paymentDetails paymentDetails = new paymentDetails();

        if (newOrder.getPaymentMethod() == paymentMethod.UPI) {
            paymentDetails.setRazorpayOrderId(generateRazorpayOrderId());
            paymentDetails.setStatus(com.example.BillingSoftware.io.paymentDetails.PaymentStatus.PENDING);
        } else {
            paymentDetails.setStatus(com.example.BillingSoftware.io.paymentDetails.PaymentStatus.COMPLETED);
        }

        newOrder.setPaymentDetails(paymentDetails);

        // 3️⃣ Convert cart items → order items & set order reference
        List<orderItemEntity> orderItems = request.getCartItems().stream()
                .map(item -> {
                    orderItemEntity entity = convertToOrderItemEntity(item);
                    entity.setOrder(orderRef);   // ✅ IMPORTANT LINE
                    return entity;
                })
                .collect(Collectors.toList());

        // 4️⃣ Attach items to order
        newOrder.setItems(orderItems);

        // 5️⃣ Save order (cascades to order items)
        newOrder = orderEntityRepository.save(newOrder);

        // 6️⃣ Return response
        return convertToResponse(newOrder);
    }


    private orderItemEntity convertToOrderItemEntity(orderRequest.orderItemRequest orderItemRequest) {
        return orderItemEntity.builder()
                .itemId(orderItemRequest.getItemId())
                .name(orderItemRequest.getName())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .build();
    }

    private orderResponse convertToResponse(orderEntity newOrder) {
        return orderResponse.builder()
                .orderId(newOrder.getOrderId())
                .customerName(newOrder.getCustomerName())
                .phoneNumber(newOrder.getPhoneNumber())
                .subTotal(newOrder.getSubTotal())
                .tax(newOrder.getTax())
                .grandTotal(newOrder.getGrandTotal())
                .paymentMethod(newOrder.getPaymentMethod())
                .items(newOrder.getItems().stream().map(this::convertToItemResponse).collect(Collectors.toList()))
                .paymentDetails(newOrder.getPaymentDetails())
                .createdAt(newOrder.getCreatedAt())
                .build();
    }

    private orderResponse.orderItemResponse convertToItemResponse(orderItemEntity orderItemEntity) {
        return orderResponse.orderItemResponse.builder()
                .itemId(orderItemEntity.getItemId())
                .name(orderItemEntity.getName())
                .price(orderItemEntity.getPrice())
                .quantity(orderItemEntity.getQuantity())
                .build();

    }

    private orderEntity convertToOrderEntity(orderRequest request) {
        return orderEntity.builder()
                .customerName(request.getCustomerName())
                .phoneNumber(request.getPhoneNumber())
                .subTotal(request.getSubTotal())
                .tax(request.getTax())
                .grandTotal(request.getGrandTotal())
                .paymentMethod(paymentMethod.valueOf(request.getPaymentMethod()))
                .build();
    }

    @Override
    public void deleteOrder(String orderId) {

        orderEntity existingOrder = orderEntityRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        orderEntityRepository.delete(existingOrder);

    }

    @Override
    public List<orderResponse> getLatestOrders() {
        return orderEntityRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public orderResponse verifyPayment(paymentVarificationRequest request) {

        orderEntity existingOrder = orderEntityRepository
                .findByOrderId(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        // ✅ Dummy verification (always true)
        if (!verifyRazorpaySignature(
                request.getRazorpayOrderId(),
                request.getRazorpayPaymentId(),
                request.getRazorpaySignature())) {

            throw new RuntimeException("Payment Verification Failed");
        }

        paymentDetails paymentDetails = existingOrder.getPaymentDetails();
        paymentDetails.setRazorpayPaymentId(request.getRazorpayPaymentId());
        paymentDetails.setRazorpaySignature(request.getRazorpaySignature());
//        paymentDetails.setStatus(com.example.BillingSoftware.io.paymentDetails.PaymentStatus.COMPLETED);
        paymentDetails.setStatus(com.example.BillingSoftware.io.paymentDetails.PaymentStatus.COMPLETED);

        existingOrder.setPaymentDetails(paymentDetails);

        existingOrder = orderEntityRepository.save(existingOrder);
        return convertToResponse(existingOrder);
    }

    private boolean verifyRazorpaySignature(
            String razorpayOrderId,
            String razorpayPaymentId,
            String razorpaySignature) {

        // ✅ Dummy mode: always success
        return true;
    }

    private String generateRazorpayOrderId() {
        return "order_" + System.currentTimeMillis() + "_" +
                UUID.randomUUID().toString().substring(0, 8);
    }


    @Override
    public Double sumSalesByDate(LocalDate date) {
        return orderEntityRepository.sumSalesByDate(date);
    }

    @Override
    public Long countByOrderdate(LocalDate date) {
        return orderEntityRepository.countByOrderDate(date);
    }

    @Override
    public List<orderResponse> findRecentOrders() {
        return orderEntityRepository.findRecentOrders()
                .stream()
                .limit(5)
                .map(orderEntity -> convertToResponse(orderEntity))
                .collect(Collectors.toList());
    }

}