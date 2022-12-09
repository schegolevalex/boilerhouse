package com.schegolevalex.boilerhouse.security.repositories;

import com.schegolevalex.boilerhouse.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name); // todo to optional
}
