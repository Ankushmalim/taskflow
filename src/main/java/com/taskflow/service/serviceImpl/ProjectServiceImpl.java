package com.taskflow.service.serviceImpl;

import com.taskflow.dto.ProjectRequestDto;
import com.taskflow.dto.ProjectResponseDto;
import com.taskflow.entity.Project;
import com.taskflow.repository.ProjectRepository;
import com.taskflow.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto) {
        Project project =  new Project();

        project.setProjectName(projectRequestDto.getProjectName());
        project.setDescription(projectRequestDto.getDescription());
        project.setOwnerId(projectRequestDto.getOwnerId());
        project.setProjectStatus(projectRequestDto.getProjectStatus());

        Project saveProject = projectRepository.save(project);

        ProjectResponseDto prd = new ProjectResponseDto();

        prd.setId(project.getId());
        prd.setProjectName(project.getProjectName());
        prd.setDescription(project.getDescription());
        prd.setOwnerId(project.getOwnerId());
        prd.setProjectStatus(String.valueOf(project.getProjectStatus()));
        prd.setCreatedAt(project.getCreatedAt());

        return prd;
    }

    @Override
    public List<ProjectResponseDto> allProject() {
        List<Project> allProject = projectRepository.findAll();
        return allProject.stream().map(project -> new ProjectResponseDto(
                project.getId(),
                project.getProjectName(),
                project.getDescription(),
                project.getOwnerId(),
                project.getProjectStatus(),
                project.getCreatedAt()
        )).collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException( "Project not found on this id : " + id));
        return new ProjectResponseDto(
                project.getId(),
                project.getProjectName(),
                project.getDescription(),
                project.getOwnerId(),
                project.getProjectStatus(),
                project.getCreatedAt()
        );
    }
}
