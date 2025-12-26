package com.example.BillingSoftware.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class orderResponse {

    private String orderId;
    private String customerName;
    private String phoneNumber;
    private paymentMethod paymentMethod;
    private List<orderResponse.orderItemResponse> items;
    private Double tax;
    private Double subtotal;
    private Double grandTotal;
    private LocalDateTime createdAt;
    private paymentDetails paymentDetails;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class orderItemResponse{
        private String itemId;
        private String name;
        private Double price;
        private Integer quantity;

    }
}
