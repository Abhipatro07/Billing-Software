package com.example.BillingSoftware.repository;

import com.example.BillingSoftware.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<userEntity, Long> {

    Optional<userEntity> findByEmail(String email);

    Optional<userEntity> findByUserId(String userId);
}
