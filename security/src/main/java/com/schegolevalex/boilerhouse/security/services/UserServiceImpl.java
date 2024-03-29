package com.schegolevalex.boilerhouse.security.services;

import com.schegolevalex.boilerhouse.security.models.Role;
import com.schegolevalex.boilerhouse.security.models.Status;
import com.schegolevalex.boilerhouse.security.models.User;
import com.schegolevalex.boilerhouse.security.repositories.RoleRepository;
import com.schegolevalex.boilerhouse.security.repositories.UserRepository;
import com.schegolevalex.boilerhouse.security.utils.AccessTokenUtil;
import com.schegolevalex.boilerhouse.security.utils.RefreshTokenUtil;
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
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("UserServiceImpl.findByUsername: user {} successfully load from database", username);
        return user;
//        if (optionalUser != null) {
//            User user = optionalUser.get();
//            log.info("UserService.findByUsername(): user with username = {} load from database", username);
//            return user;
//        } else {
//            log.info("UserService.findByUsername(): user with username = {} not found in database", username);
//            return null;
//        }
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
        User userFromDB = findByUsername(incomeUser.getUsername());

        if (userFromDB != null)
            errors.rejectValue("userName", "", "User with username " +
                    incomeUser.getUsername() + " already exist");
    }
}

