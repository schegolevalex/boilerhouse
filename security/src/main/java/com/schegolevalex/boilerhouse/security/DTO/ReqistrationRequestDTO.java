package com.schegolevalex.boilerhouse.security.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ReqistrationRequestDTO {
    @Size(min = 2, max = 30, message = "Username must contain between 2 and 30 letters")
    @NotEmpty(message = "Username name shouldn't be empty")
    String userName;

    @Size(min = 2, max = 30, message = "First name must contain between 2 and 30 letters")
    @NotEmpty(message = "First name shouldn't be empty")
    String firstName;

    @Size(min = 2, max = 30, message = "Last name must contain between 2 and 30 letters")
    @NotEmpty(message = "Last name shouldn't be empty")
    String lastName;

    LocalDate birthday;

    @Email(message = "Invalid email")
    String email;

    String password;

}
