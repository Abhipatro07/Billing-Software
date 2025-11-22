package com.example.BillingSoftware.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class authResponse {
    private String email;
    private String role;
    private String token;
}
