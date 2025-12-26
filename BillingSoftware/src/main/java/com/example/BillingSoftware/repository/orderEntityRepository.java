package com.example.BillingSoftware.repository;

import com.example.BillingSoftware.entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface orderEntityRepository extends JpaRepository<orderEntity, Long> {
    Optional<orderEntity> findByOrderId(String orderId);

    List<orderEntity> findAllByOrderByCreatedAtDesc();
}
