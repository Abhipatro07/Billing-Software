package com.example.BillingSoftware.entity;

import com.example.BillingSoftware.io.paymentDetails;
import com.example.BillingSoftware.io.paymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_orders")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class orderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private String customerName;
    private String phoneNumber;
    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<orderItemEntity> items = new ArrayList<>();

    @Embedded
    private paymentDetails paymentDetails;

    @Enumerated(EnumType.STRING)
    private paymentMethod paymentMethod;

    @PrePersist
    protected void onCreate(){
        this.orderId = "ORD" + System.currentTimeMillis();
        this.createdAt = LocalDateTime.now();
    }
}
