package com.schegolevalex.heat_engineering_calculations.security;

import com.schegolevalex.heat_engineering_calculations.models.RefreshToken;
import com.schegolevalex.heat_engineering_calculations.models.Status;
import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.repositories.RefreshTokenRepository;
import com.schegolevalex.heat_engineering_calculations.security.exceptions.RefreshTokenVerificationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenUtil {

    @Value("${jwt.refresh_token.expiration_time}")
    Long refreshTokenExpirationTime;

    final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenUtil(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(),
                OffsetDateTime.now().plusSeconds(refreshTokenExpirationTime / 100),
                user);
        refreshToken.setCreatedAt(OffsetDateTime.now());
        refreshToken.setUpdatedAt(OffsetDateTime.now());
        refreshToken.setStatus(Status.ACTIVE);

        user.setRefreshToken(refreshToken);

        return refreshTokenRepository.save(refreshToken);
    }

    public User getUserFromRefreshToken(String refreshToken) {
        Optional<RefreshToken> byValue = refreshTokenRepository.findByValue(refreshToken);
        if (byValue.isPresent()) {
            return byValue.get().getUser();
        } else {
            throw new RefreshTokenVerificationException("There is no such refresh token");
        }
    }
}
