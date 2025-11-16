package com.example.BillingSoftware.repository;

import com.example.BillingSoftware.entity.categoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface categoryRepository extends JpaRepository<categoryEntity, Long> {

    Optional<categoryEntity> findByCategoryId(String categoryId);

}
