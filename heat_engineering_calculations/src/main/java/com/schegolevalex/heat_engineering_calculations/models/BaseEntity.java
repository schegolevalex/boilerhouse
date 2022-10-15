package com.schegolevalex.heat_engineering_calculations.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created")
    LocalDateTime createdAt;

    @Column(name = "updated")
    LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
}
