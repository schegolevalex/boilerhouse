package com.schegolevalex.heat_engineering_calculations.services;

import com.schegolevalex.heat_engineering_calculations.models.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Service
public class UserValidatorService implements Validator {
    final UserDetailsServiceImpl userDetailsService;

    public UserValidatorService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User incomeUser = (User) target;
        Optional<User> userFromDB = userDetailsService.findByUserName(incomeUser.getUserName());

        if (userFromDB.isPresent())
            errors.rejectValue("username", "User with username " +
                    incomeUser.getUserName() + " already exist");
    }
}
