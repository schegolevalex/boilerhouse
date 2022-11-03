package com.schegolevalex.heat_engineering_calculations.services;

import com.schegolevalex.heat_engineering_calculations.models.Role;
import com.schegolevalex.heat_engineering_calculations.models.Status;
import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.repositories.RoleRepository;
import com.schegolevalex.heat_engineering_calculations.repositories.UserRepository;
import com.schegolevalex.heat_engineering_calculations.security.AccessTokenUtil;
import com.schegolevalex.heat_engineering_calculations.security.RefreshTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;
    final AccessTokenUtil accessTokenUtil;
    final RefreshTokenUtil refreshTokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           AccessTokenUtil accessTokenUtil,
                           RefreshTokenUtil refreshTokenUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenUtil = accessTokenUtil;
        this.refreshTokenUtil = refreshTokenUtil;
    }

    @Override
    @Transactional
    public User register(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRefreshToken(refreshTokenUtil.generateRefreshToken(user));

        User registeredUser = userRepository.save(user);
        log.info("UserService.register(): user {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
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
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        User incomeUser = (User) target;
        Optional<User> userFromDB = findByUsername(incomeUser.getUsername());

        if (userFromDB.isPresent())
            errors.rejectValue("userName", "", "User with username " +
                    incomeUser.getUsername() + " already exist");
    }
}

