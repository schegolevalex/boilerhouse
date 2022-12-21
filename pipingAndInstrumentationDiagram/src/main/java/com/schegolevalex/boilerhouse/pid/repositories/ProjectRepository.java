package com.schegolevalex.boilerhouse.pid.repositories;

import com.schegolevalex.boilerhouse.pid.models.Project;
import com.schegolevalex.boilerhouse.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User owner);
    Project findByOwnerAndId(User owner, Long id);
    void deleteByOwnerAndId(User owner, Long id);
}
