package com.example.BillingSoftware.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class categoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String categoryId;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "bg_color")
    private String bgColor;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
}
