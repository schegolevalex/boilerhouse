package com.schegolevalex.boilerhouse.pid.controllers;

import com.schegolevalex.boilerhouse.pid.models.Project;
import com.schegolevalex.boilerhouse.pid.services.pid.ProjectService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/pid")
public class PIDController {

    final ProjectService projectService;

    public PIDController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<Project> getAllUserProjects() {
        return projectService.getAllUserProjects();
    }

    @GetMapping("/projects/{projectId}")
    public Project getUserProject(@PathVariable Long projectId) {
        return projectService.getUserProject(projectId);
    }

    @PostMapping("/projects")
    public Project createNewProject(@RequestBody String name) {
        return projectService.createNewProject(name);
    }

    @DeleteMapping("/projects/{projectId}")
    public void deleteUserProject(@PathVariable Long projectId) {
        projectService.deleteById(projectId);
    }
}