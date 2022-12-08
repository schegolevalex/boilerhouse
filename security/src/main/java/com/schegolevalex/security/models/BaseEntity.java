package com.schegolevalex.security.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created")
    OffsetDateTime createdAt;

    @Column(name = "updated")
    OffsetDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
}
