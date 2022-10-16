package com.schegolevalex.heat_engineering_calculations.repositories;

import com.schegolevalex.heat_engineering_calculations.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
