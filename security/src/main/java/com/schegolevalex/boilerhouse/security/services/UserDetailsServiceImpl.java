package com.schegolevalex.boilerhouse.security.services;

import com.schegolevalex.boilerhouse.security.models.User;
import com.schegolevalex.boilerhouse.security.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {
    final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

//        if (user.isEmpty()) {
//            log.warn("JWTUserDetailsService.loadByUsername: user with name \"{}\" not found in database", username);
//            throw new UsernameNotFoundException("User with username " + username + " not found in database");
//        }

        log.info("JWTUserDetailsService.loadByUsername: user {} successfully load from database", username);
        return new UserDetailsImpl(user);
    }
}
