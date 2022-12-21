package com.schegolevalex.boilerhouse.security.services;

import com.schegolevalex.boilerhouse.security.models.User;
import org.springframework.validation.Validator;


public interface UserService extends Validator {
    User register(User user);

    User findByUsername(String userName);

    void deleteById (Long id);
}
