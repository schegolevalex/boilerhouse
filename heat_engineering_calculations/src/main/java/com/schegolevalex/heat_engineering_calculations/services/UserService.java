package com.schegolevalex.heat_engineering_calculations.services;

import com.schegolevalex.heat_engineering_calculations.models.User;
import org.springframework.validation.Validator;

import java.util.Optional;

public interface UserService extends Validator {
    User register(User user);

    Optional<User> findByUsername(String userName);

    void deleteById (Long id);
}
