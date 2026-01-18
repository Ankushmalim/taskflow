package com.taskflow.dto;

import com.taskflow.entity.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto
{
    @NotBlank(message = "Project name is required!")
    private String projectName;

    @NotBlank(message = "Description is required!")
    private String description;

    @NotBlank(message = "Owner Id is required!")
    private Long ownerId;

    @NotBlank(message = "Project Status is required!")
    private ProjectStatus projectStatus;
}
