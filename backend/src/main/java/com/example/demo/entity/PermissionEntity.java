package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
