package com.example.BillingSoftware.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Resolve to absolute path → file:/C:/.../uploads/ or similar
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        String resourceLocation = uploadPath.toUri().toString();  // e.g. "file:/C:/project/uploads/"

        registry
                .addResourceHandler("/uploads/**")
                .addResourceLocations(resourceLocation)
                .setCachePeriod(0);
    }
}
