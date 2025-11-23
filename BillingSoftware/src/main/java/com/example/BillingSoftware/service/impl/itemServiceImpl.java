package com.example.BillingSoftware.service.impl;

import com.example.BillingSoftware.entity.categoryEntity;
import com.example.BillingSoftware.entity.itemEntity;
import com.example.BillingSoftware.io.itemRequest;
import com.example.BillingSoftware.io.itemResponse;
import com.example.BillingSoftware.repository.categoryRepository;
import com.example.BillingSoftware.repository.itemRepository;
import com.example.BillingSoftware.service.fileUploadService;
import com.example.BillingSoftware.service.itemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class itemServiceImpl implements itemService {

    private final fileUploadService fileUploadService;
    private final categoryRepository categoryRepository;
    private final itemRepository itemRepository;

    @Override
    public itemResponse add(itemRequest request, MultipartFile file) {
        String imageUrl = fileUploadService.uploadImage(file);
        itemEntity newItem = convertTOEntity(request);
        categoryEntity existingCategory = categoryRepository.findByCategoryId(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found: " + request.getCategoryId()));

        newItem.setCategory(existingCategory);
        newItem.setImageUrl(imageUrl);
        newItem = itemRepository.save(newItem);
        return convertToResponse(newItem);
    }

    private itemResponse convertToResponse(itemEntity newItem) {
        return itemResponse.builder()
                .itemId(newItem.getItemId())
                .name(newItem.getName())
                .description((newItem.getDescription()))
                .price(newItem.getPrice())
                .imageUrl(newItem.getImageUrl())
                .categoryName(newItem.getCategory().getCategoryId())
                .createdAt(newItem.getCreatedAt())
                .updatedAt(newItem.getUpdatedAt())
                .build();
    }

    private itemEntity convertTOEntity(itemRequest request) {
        return itemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    @Override
    public List<itemResponse> fetchItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemEntity -> convertToResponse(itemEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(String itemId) {
        itemEntity existingItem = itemRepository.findByItemId(itemId)
                .orElseThrow(() ->new RuntimeException( "Item Not Found: " + itemId));

        boolean isFileDelete = fileUploadService.deleteFile(existingItem.getImageUrl());
        if(isFileDelete){
            itemRepository.delete(existingItem);
        }

        else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Unable to delete the Image");
        }
    }
}
