package com.example.BillingSoftware.io;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class categoryRequest {
    private String name;
    private String description;
    private String bgColor;
}
