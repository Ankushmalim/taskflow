package com.taskflow.controller;

import com.taskflow.dto.ProjectRequestDto;
import com.taskflow.dto.ProjectResponseDto;
import com.taskflow.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto){
        ProjectResponseDto project = projectService.createProject(projectRequestDto);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProject (){
        List<ProjectResponseDto> projectResponseDtos = projectService.allProject();
        return new ResponseEntity<>(projectResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<ProjectResponseDto> getProjectById(@RequestParam Long id){
        ProjectResponseDto projectById = projectService.getProjectById(id);
        return new ResponseEntity<>(projectById, HttpStatus.OK);
    }
}
