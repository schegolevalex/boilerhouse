package com.schegolevalex.heat_engineering_calculations.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {
    @JsonProperty(value = "username")
    String userName;

    @JsonProperty(value = "jwt-token")
    String jwtToken;
}
