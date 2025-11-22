package com.example.BillingSoftware.service;

import com.example.BillingSoftware.io.userRequest;
import com.example.BillingSoftware.io.userResponse;

import java.util.List;

public interface userService {
    userResponse createUser(userRequest request);

    String getUserRole(String email);

    List<userResponse> readUsers();

    void deleteUser(String id);
}
