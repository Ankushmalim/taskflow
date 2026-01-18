package com.taskflow.service;

import com.taskflow.dto.ProjectRequestDto;
import com.taskflow.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto);
    public List<ProjectResponseDto> allProject();
    public ProjectResponseDto getProjectById(Long id);
}
