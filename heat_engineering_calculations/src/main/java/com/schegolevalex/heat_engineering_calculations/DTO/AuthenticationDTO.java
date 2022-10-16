package com.schegolevalex.heat_engineering_calculations.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AuthenticationDTO {
    @Size(min = 2, max = 30, message = "Username must contain between 2 and 30 letters")
    @NotEmpty(message = "Username name shouldn't be empty")
    String userName;

    String password;

}
