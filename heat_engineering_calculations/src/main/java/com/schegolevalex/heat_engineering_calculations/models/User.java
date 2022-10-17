package com.schegolevalex.heat_engineering_calculations.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Column(name = "username")
    @Size(min = 2, max = 30, message = "Username must contain between 2 and 30 letters")
    @NotEmpty(message = "Username name shouldn't be empty")
    String userName;

    @Column(name = "first_name")
    @Size(min = 2, max = 30, message = "First name must contain between 2 and 30 letters")
    @NotEmpty(message = "First name shouldn't be empty")
    String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 30, message = "Last name must contain between 2 and 30 letters")
    @NotEmpty(message = "Last name shouldn't be empty")
    String lastName;

    @Column(name = "birthday")
//    @Min(value = 1930, message = "Year of birth should be greater than 1930")
    @CreatedDate
    LocalDate birthday;

    @Column(name = "email")
    @Email(message = "Invalid email")
    String email;

    @Column(name = "password")
    String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    List<Role> roles;
}
