package com.example.BillingSoftware.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class dashboardResponse {

    private Double todaySales;
    private Long todayOrderCount;
    private List<orderResponse> recentOrders;
}
