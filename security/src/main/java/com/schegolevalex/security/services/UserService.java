package com.schegolevalex.security.services;

import com.schegolevalex.security.models.User;
import org.springframework.validation.Validator;

import java.util.Optional;


public interface UserService extends Validator {
    User register(User user);

    Optional<User> findByUsername(String userName);

    void deleteById (Long id);
}
