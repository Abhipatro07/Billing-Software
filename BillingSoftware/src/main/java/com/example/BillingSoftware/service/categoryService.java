package com.example.BillingSoftware.service;


import com.example.BillingSoftware.io.categoryRequest;
import com.example.BillingSoftware.io.categoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface categoryService {
    categoryResponse add(categoryRequest request, MultipartFile imageFile);

    String uploadImage(MultipartFile file);

    List<categoryResponse> read();

    void delete(String categoryId);
}
