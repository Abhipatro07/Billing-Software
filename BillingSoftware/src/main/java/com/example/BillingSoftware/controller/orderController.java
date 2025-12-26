package com.example.BillingSoftware.controller;

import com.example.BillingSoftware.io.orderRequest;
import com.example.BillingSoftware.io.orderResponse;
import com.example.BillingSoftware.service.orderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class orderController {

    private final orderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public orderResponse createOrder(@RequestBody orderRequest request){
        return orderService.createOrder(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/latest")
    public List<orderResponse> getLatestOrders(){
        return orderService.getLatestOrders();
    }
}
