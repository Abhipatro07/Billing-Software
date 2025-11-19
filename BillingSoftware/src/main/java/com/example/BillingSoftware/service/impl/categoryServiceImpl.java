package com.example.BillingSoftware.service.impl;

import com.example.BillingSoftware.entity.categoryEntity;
import com.example.BillingSoftware.io.categoryRequest;
import com.example.BillingSoftware.io.categoryResponse;
import com.example.BillingSoftware.repository.categoryRepository;
import com.example.BillingSoftware.service.categoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class categoryServiceImpl implements categoryService {
    private final categoryRepository categoryRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public categoryResponse add(categoryRequest request, MultipartFile imageFile) {

        String imageUrl = null;

        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = uploadImage(imageFile);
        }

        categoryEntity newCategory = categoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .imageUrl(imageUrl)
                .build();

        newCategory = categoryRepository.save(newCategory);

        return convertToResponse(newCategory);
    }

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) directory.mkdirs();

            String fileName =
                    UUID.randomUUID() + "_" +
                            file.getOriginalFilename().replace(" ", "_");

            Path filePath = Paths.get(uploadDir + "/" + fileName);

            Files.write(filePath, file.getBytes());

            return "/" + uploadDir + "/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Image Upload Failed: " + e.getMessage());
        }
    }

    @Override
    public List<categoryResponse> read() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> convertToResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
        categoryEntity existingCategory =  categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not Found: " + categoryId));

        categoryRepository.delete(existingCategory);
    }

    private categoryResponse convertToResponse(categoryEntity newCategory) {
        return categoryResponse.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor((newCategory.getBgColor()))
                .imageUrl(newCategory.getImageUrl())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .build();
    }

    private categoryEntity convertToEntity(categoryRequest request) {
        return categoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
