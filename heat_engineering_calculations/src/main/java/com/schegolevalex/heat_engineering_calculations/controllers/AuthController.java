package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.DTO.AuthenticationDTO;
import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.security.JWTUtil;
import com.schegolevalex.heat_engineering_calculations.services.UserRegistrationService;
import com.schegolevalex.heat_engineering_calculations.services.UserValidatorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final UserRegistrationService userRegistrationService;
    final UserValidatorService userValidatorService;
    final JWTUtil jwtUtil;
    final ModelMapper modelMapper;
    final AuthenticationManager authManager;

    public AuthController(ModelMapper modelMapper,
                          JWTUtil jwtUtil,
                          UserValidatorService userValidatorService,
                          UserRegistrationService userRegistrationService,
                          AuthenticationManager authManager) {
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.userValidatorService = userValidatorService;
        this.userRegistrationService = userRegistrationService;
        this.authManager = authManager;
    }

    @PostMapping("/registration")
    public String performRegistration(@RequestBody @Valid User user,
                                      BindingResult bindingResult) {
        userValidatorService.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            //todo
        }
        userRegistrationService.register(user);
        //todo
        return null;
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(AuthenticationDTO authDTO) {
        UsernamePasswordAuthenticationToken authInputToken
                = new UsernamePasswordAuthenticationToken(authDTO.getUserName(), authDTO.getPassword());
        authManager.authenticate(authInputToken);
        return Map.of("jwt-token", jwtUtil.generateToken(authDTO.getUserName()));
    }

//    @PostMapping("/logout")
//    public String logout(AuthenticationDTO authDTO) {
//
//    }

}
