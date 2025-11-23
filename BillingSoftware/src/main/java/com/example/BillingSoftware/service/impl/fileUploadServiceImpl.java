package com.example.BillingSoftware.service.impl;

import com.example.BillingSoftware.service.fileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class fileUploadServiceImpl implements fileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" +
                    file.getOriginalFilename().replace(" ", "_");

            Path filePath = Paths.get(uploadDir + "/" + fileName);
            Files.write(filePath, file.getBytes());

            // this is what you store in DB / return to frontend
            return "/" + uploadDir + "/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Image Upload Failed: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteFile(String filePath) {
        try {
            if (filePath == null || filePath.isEmpty()) {
                return false;
            }

            // Remove leading "/" if present
            if (filePath.startsWith("/")) {
                filePath = filePath.substring(1);
            }

            File file = new File(filePath);

            if (file.exists()) {
                return file.delete();   // returns true / false
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
