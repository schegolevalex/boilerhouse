package com.schegolevalex.boilerhouse.security.repositories;

import com.schegolevalex.boilerhouse.security.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByValue(String value);
}
