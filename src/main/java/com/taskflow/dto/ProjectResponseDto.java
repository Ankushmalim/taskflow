package com.taskflow.dto;

import com.taskflow.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
    private Long id;
    private String projectName;
    private String description;
    private Long ownerId;
    private String projectStatus;
    private LocalDateTime createdAt;

    public ProjectResponseDto(Long id, String projectName, String description, Long ownerId, ProjectStatus projectStatus, LocalDateTime createdAt) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.ownerId = ownerId;
        this.projectStatus = projectStatus.name();
        this.createdAt = createdAt;
    }
}
