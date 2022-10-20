package com.schegolevalex.heat_engineering_calculations.security;

import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTUserDetailsService implements UserDetailsService {
    final UserRepository userRepository;

    @Autowired
    public JWTUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
//    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        System.out.println("********************");
        System.out.println(user);
        System.out.println("********************");

        if (user.isEmpty()) {
            log.warn("JWTUserDetailsService.loadByUsername: user with name \"{}\" not found in database", userName);
            throw new UsernameNotFoundException("User with username " + userName + " not found in database");
        }

        log.info("JWTUserDetailsService.loadByUsername: user {} successfully load from database", userName);
        return new UserDetailsImpl(user.get());
    }
}
