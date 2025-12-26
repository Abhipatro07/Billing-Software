package com.example.BillingSoftware.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class paymentRequest {
    private Double amount;
    private String currency;
}
