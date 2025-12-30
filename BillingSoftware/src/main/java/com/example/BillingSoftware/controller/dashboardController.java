package com.example.BillingSoftware.controller;

import com.example.BillingSoftware.io.dashboardResponse;
import com.example.BillingSoftware.io.orderResponse;
import com.example.BillingSoftware.service.orderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class dashboardController {

    private final orderService orderService;

    @GetMapping
    public dashboardResponse getDashboardData(){
        LocalDate toady = LocalDate.now();
        Double todaySale = orderService.sumSalesByDate(toady);
        Long todayOrderCount = orderService.countByOrderdate(toady);
        List<orderResponse> recentOrders = orderService.findRecentOrders();
        return new dashboardResponse(
                todaySale != null ? todaySale : 0.0 ,
                todayOrderCount != null? todayOrderCount : 0,
                recentOrders
        );
    }
}
