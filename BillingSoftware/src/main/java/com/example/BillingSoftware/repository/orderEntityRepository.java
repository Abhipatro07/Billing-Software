package com.example.BillingSoftware.repository;

import com.example.BillingSoftware.entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface orderEntityRepository extends JpaRepository<orderEntity, Long> {
    Optional<orderEntity> findByOrderId(String orderId);

    List<orderEntity> findAllByOrderByCreatedAtDesc();

    @Query("SELECT SUM(o.grandTotal) FROM orderEntity o WHERE DATE(o.createdAt) = :date")
    Double sumSalesByDate(@Param("date")LocalDate date);

    @Query("SELECT COUNT(o) FROM orderEntity o WHERE DATE(o.createdAt) = :date")
    Long countByOrderDate(@Param("date") LocalDate date);

    @Query("SELECT o FROM orderEntity o ORDER BY o.createdAt DESC")
    List<orderEntity> findRecentOrders();
}
