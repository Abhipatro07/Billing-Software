package com.example.BillingSoftware.service.impl;

import com.example.BillingSoftware.entity.userEntity;
import com.example.BillingSoftware.io.userRequest;
import com.example.BillingSoftware.io.userResponse;
import com.example.BillingSoftware.repository.userRepository;
import com.example.BillingSoftware.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class userServiceImpl implements userService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public userResponse createUser(userRequest request) {
        userEntity newUser = convertToEntity(request);
        newUser = userRepository.save(newUser);
        return convertToResponse(newUser);
    }

    private userResponse convertToResponse(userEntity newUser) {
        return userResponse.builder()
                .userId(newUser.getUserId())
                .role(newUser.getRole())
                .name(newUser.getName())
                .email(newUser.getEmail())
                .createdAt(newUser.getCreatedAt())
                .updatedAt(newUser.getUpdatedAt())
                .build();
    }

    private userEntity convertToEntity(userRequest request) {
        return userEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase())
                .name(request.getName())
                .build();
    }

    @Override
    public String getUserRole(String email) {
        userEntity existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for this Email: " + email));
        return existingUser.getRole();
    }

    @Override
    public List<userResponse> readUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> convertToResponse(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
        userEntity existingUser = userRepository.findByUserId(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(existingUser);
    }
}
