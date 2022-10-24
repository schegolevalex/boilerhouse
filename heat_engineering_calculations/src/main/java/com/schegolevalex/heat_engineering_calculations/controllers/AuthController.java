package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.DTO.AuthRequestDTO;
import com.schegolevalex.heat_engineering_calculations.DTO.AuthResponseDTO;
import com.schegolevalex.heat_engineering_calculations.DTO.ReqistrationRequestDTO;
import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.security.JWTUtil;
import com.schegolevalex.heat_engineering_calculations.security.UserDetailsImpl;
import com.schegolevalex.heat_engineering_calculations.security.exceptions.UserRegistrationException;
import com.schegolevalex.heat_engineering_calculations.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<AuthResponseDTO> registration(@RequestBody @Valid ReqistrationRequestDTO reqistrationRequestDTO,
                                                        BindingResult bindingResult) throws UserRegistrationException {
        User user = modelMapper.map(reqistrationRequestDTO, User.class);
        userService.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            throw new UserRegistrationException("Registration exception");

        User registeredUser = userService.register(user);

        UserDetails registeredUserDetails = new UserDetailsImpl(registeredUser);

        String accessToken = jwtUtil.generateAccessToken(registeredUserDetails.getUsername(),
                registeredUserDetails.getAuthorities());

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(reqistrationRequestDTO.getUserName(), accessToken);

        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginProcessing(@RequestBody AuthRequestDTO authDTO) {
        UsernamePasswordAuthenticationToken authInputToken
                = new UsernamePasswordAuthenticationToken(authDTO.getUserName(), authDTO.getPassword());
        Authentication authentication = authManager.authenticate(authInputToken);

        String accessToken = jwtUtil.generateAccessToken(((UserDetailsImpl)authentication.getPrincipal()).getUsername(),
                authentication.getAuthorities());

        AuthResponseDTO authResponseDTO
                = new AuthResponseDTO(((UserDetailsImpl)authentication.getPrincipal()).getUsername(), accessToken);

        return ResponseEntity.ok(authResponseDTO);
    }

    //todo сделать обновление токена (лучше сразу имеющийся сделать accessToken и создать второй - refreshToken),
    // но сначала узнать насчет мест хранения токенов. AccessToken сейчас я передаю в обычном JSON, видимо он будет
    // храниться в local storage. Но вроде как refreshToken должен храниться в local storage, а accessToken надо
    // отдавать в cookies... Но тогда при наличии сессий (cookies) как будет решаться вопрос с микросервисной
    // архитектурой?

}
