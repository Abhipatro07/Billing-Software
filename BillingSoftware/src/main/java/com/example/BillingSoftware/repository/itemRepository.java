package com.example.BillingSoftware.repository;

import com.example.BillingSoftware.entity.itemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface itemRepository extends JpaRepository<itemEntity, Long> {
    Optional<itemEntity> findByItemId(String itemId);

    Integer countByCategoryId(Long id);
}
