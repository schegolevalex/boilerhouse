package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.DTO.AuthenticationDTO;
import com.schegolevalex.heat_engineering_calculations.DTO.UserDTO;
import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.security.JWTUtil;
import com.schegolevalex.heat_engineering_calculations.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final UserService userService;
    final JWTUtil jwtUtil;
    final ModelMapper modelMapper;
    final AuthenticationManager authManager;

    public AuthController(ModelMapper modelMapper,
                          JWTUtil jwtUtil,
                          UserService userService,
                          AuthenticationManager authManager) {
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.authManager = authManager;
    }

    @PostMapping("/registration")
    public Map<String, String> registration(@RequestBody @Valid UserDTO userDTO,
                                            BindingResult bindingResult) throws Exception {
        User user = modelMapper.map(userDTO, User.class);
        userService.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            throw new Exception();
        userService.register(user);
        return Map.of("jwt-token", jwtUtil.generateToken(user.getUserName()));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(AuthenticationDTO authDTO) {
        UsernamePasswordAuthenticationToken authInputToken
                = new UsernamePasswordAuthenticationToken(authDTO.getUserName(), authDTO.getPassword());
        authManager.authenticate(authInputToken);

        Map<String, String> response = new HashMap<>();
        response.put("user", authDTO.getUserName());
        response.put("jwt-token", jwtUtil.generateToken(authDTO.getUserName()));

        return ResponseEntity.ok(response);
    }

    //todo сделать logout
    @PostMapping("/logout")
    public String logout(AuthenticationDTO authDTO) {
        return null;
    }

    //todo сделать обновление токена (лучше сразу имеющийся сделать accessToken и создать второй - refreshToken),
    // но сначала узнать насчет мест хранения токенов. AccessToken сейчас я передаю в обычном JSON, видимо он будет
    // храниться в local storage. Но вроде как refreshToken должен храниться в local storage, а accessToken надо
    // отдавать в cookies... Но тогда при наличии сессий (cookies) как будет решаться вопрос с микросервисной
    // архитектурой?

}
