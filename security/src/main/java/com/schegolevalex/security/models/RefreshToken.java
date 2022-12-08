package com.schegolevalex.security.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "refresh_tokens")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends BaseEntity {
    @Column(name = "refresh_token")
    String value;

    @Column(name = "expired")
    OffsetDateTime expiredAt;
}
