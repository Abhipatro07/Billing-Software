package com.example.BillingSoftware.service;

import org.springframework.web.multipart.MultipartFile;

public interface fileUploadService {
    String uploadImage(MultipartFile file);

    boolean deleteFile(String filePath);
}
