package com.example.BillingSoftware.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class userResponse {
    private String userId;
    private String name;
    private String email;
    private String role;
    private Timestamp updatedAt;
    private Timestamp createdAt;
}
