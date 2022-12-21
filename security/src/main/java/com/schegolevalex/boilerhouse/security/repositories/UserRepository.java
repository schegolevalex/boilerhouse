package com.schegolevalex.boilerhouse.security.repositories;

import com.schegolevalex.boilerhouse.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    // todo пересмотреть
    @Query(value = "select * from users u join refresh_tokens r on (u.refresh_token = r.id) where r.refresh_token = :refreshToken",
            nativeQuery = true)
    User findByRefreshTokenValue(@Param("refreshToken") String refreshToken);
}
