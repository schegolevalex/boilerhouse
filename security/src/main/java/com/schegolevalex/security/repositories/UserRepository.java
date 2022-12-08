package com.schegolevalex.security.repositories;

import com.schegolevalex.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "select * from users u join refresh_tokens r on (u.refresh_token = r.id) where r.refresh_token = :refreshToken",
            nativeQuery = true)
    Optional<User> findByRefreshTokenValue(@Param("refreshToken") String refreshToken);
}
