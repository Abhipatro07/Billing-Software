package com.example.BillingSoftware.service;

import com.example.BillingSoftware.io.itemRequest;
import com.example.BillingSoftware.io.itemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface itemService {
    itemResponse add(itemRequest request , MultipartFile file);

    List<itemResponse> fetchItems();

    void deleteItem(String itemId);

}
