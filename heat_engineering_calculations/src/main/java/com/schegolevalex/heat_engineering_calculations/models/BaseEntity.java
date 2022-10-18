package com.schegolevalex.heat_engineering_calculations.models;

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
//    @CreatedDate
    OffsetDateTime createdAt;

    @Column(name = "updated")
//    @LastModifiedDate
    OffsetDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
}
