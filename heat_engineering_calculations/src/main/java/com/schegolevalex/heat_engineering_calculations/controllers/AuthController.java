package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.security.JWTUtil;
import com.schegolevalex.heat_engineering_calculations.services.UserRegistrationService;
import com.schegolevalex.heat_engineering_calculations.services.UserValidatorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final UserRegistrationService userRegistrationService;
    final UserValidatorService userValidatorService;
    final JWTUtil jwtUtil;
    final ModelMapper modelMapper;

    public AuthController(ModelMapper modelMapper,
                          JWTUtil jwtUtil,
                          UserValidatorService userValidatorService,
                          UserRegistrationService userRegistrationService) {
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.userValidatorService = userValidatorService;
        this.userRegistrationService = userRegistrationService;
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


}
