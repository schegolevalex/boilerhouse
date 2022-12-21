package com.schegolevalex.boilerhouse.pid.services.pid;

import com.schegolevalex.boilerhouse.pid.models.Project;
import com.schegolevalex.boilerhouse.pid.repositories.ProjectRepository;
import com.schegolevalex.boilerhouse.security.models.User;
import com.schegolevalex.boilerhouse.security.services.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectService {
    final ProjectRepository projectRepository;
    final UserDetailsService userDetailsService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          UserDetailsService userDetailsService) {
        this.projectRepository = projectRepository;
        this.userDetailsService = userDetailsService;
    }

    public Project createNewProject(String name) {
        Project newProject = Project.builder().withName(name).withOwner(getUser()).build();
        Project savedNewProject = projectRepository.save(newProject);
        log.info("Project \"{}\" was successfully saved to database", savedNewProject.getName());
        return savedNewProject;
    }

    public List<Project> getAllUserProjects() {
        return projectRepository.findByOwner(getUser());
    }

    public Project getUserProject(Long projectId) {
        return projectRepository.findByOwnerAndId(getUser(), projectId);
    }

    public void deleteById(Long projectId) {
        projectRepository.deleteByOwnerAndId(getUser(), projectId);
    }

    private User getUser() {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return ((UserDetailsImpl) userDetailsService.loadUserByUsername(username)).getUser();
    }
}
