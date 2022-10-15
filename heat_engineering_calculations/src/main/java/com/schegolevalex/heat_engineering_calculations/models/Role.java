package com.schegolevalex.heat_engineering_calculations.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Role extends BaseEntity {
    @Column(name = "name")
    String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
