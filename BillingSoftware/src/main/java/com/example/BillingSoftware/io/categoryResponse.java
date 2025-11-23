package com.example.BillingSoftware.io;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class categoryResponse {
    private String categoryId;
    private String name;
    private String description;
    private String bgColor;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String imageUrl;
    private Integer items;
}
