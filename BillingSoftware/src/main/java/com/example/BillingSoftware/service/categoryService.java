package com.example.BillingSoftware.service;


import com.example.BillingSoftware.io.categoryRequest;
import com.example.BillingSoftware.io.categoryResponse;

import java.util.List;

public interface categoryService {
    categoryResponse add(categoryRequest request);

    List<categoryResponse> read();

    void delete(String categoryId);
}
