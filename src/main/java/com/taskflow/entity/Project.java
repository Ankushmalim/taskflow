package com.taskflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "projectName", nullable = false)
    private String projectName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

    @Column(name = "projectStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @Column(nullable = false, updatable = false, name = "createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
