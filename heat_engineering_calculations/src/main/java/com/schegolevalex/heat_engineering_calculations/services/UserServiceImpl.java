package com.schegolevalex.heat_engineering_calculations.services;

import com.schegolevalex.heat_engineering_calculations.models.Role;
import com.schegolevalex.heat_engineering_calculations.models.Status;
import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.repositories.RoleRepository;
import com.schegolevalex.heat_engineering_calculations.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("UserService.register(): user {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("UserService.deleteById(): user with id = {} successfully deleted from database", id);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User incomeUser = (User) target;
        Optional<User> userFromDB = findByUserName(incomeUser.getUserName());

        if (userFromDB.isPresent())
            errors.rejectValue("username", "User with username " +
                    incomeUser.getUserName() + " already exist");
    }
}

