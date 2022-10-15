package com.schegolevalex.heat_engineering_calculations.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty(message = "First name shouldn't be empty")
    String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name shouldn't be empty")
    String lastName;

    @Column(name = "birthday")
    @Min(value = 1930, message = "Year of birth should be greater than 1930")
    LocalDateTime birthday;p

    @Column(name = "email")
    @Email(message = "Invalid email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "roles")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    List<Role> roles;
}
