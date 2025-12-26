package com.example.BillingSoftware.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class orderRequest {

    private String customerName;
    private String phoneNumber;
    private String paymentMethod;
    private List<orderItemRequest> cartItems;
    private Double tax;
    private Double subtotal;
    private Double grandTotal;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class orderItemRequest{
        private String itemId;
        private String name;
        private Double price;
        private Integer quantity;

    }
}
