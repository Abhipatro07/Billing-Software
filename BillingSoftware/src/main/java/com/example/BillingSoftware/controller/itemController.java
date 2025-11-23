package com.example.BillingSoftware.controller;

import com.example.BillingSoftware.io.itemRequest;
import com.example.BillingSoftware.io.itemResponse;
import com.example.BillingSoftware.service.itemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class itemController {

    private final itemService itemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/items")
    public itemResponse add(
            @RequestPart("item") String itemString,
            @RequestPart(value = "imageFile", required = false) MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            itemRequest request = objectMapper.readValue(itemString, itemRequest.class);
            return itemService.add(request, file);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid item JSON");
        }
    }


    @GetMapping("/items")
    public List<itemResponse> readItem(){
        return itemService.fetchItems();
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/items/{itemId}")
    public void removeItem(@PathVariable String itemId){
        try{
            itemService.deleteItem(itemId);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Item not Found");
        }
    }
}
