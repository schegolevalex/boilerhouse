package com.schegolevalex.heat_engineering_calculations.controllers;

import com.schegolevalex.heat_engineering_calculations.DTO.AuthRequestDTO;
import com.schegolevalex.heat_engineering_calculations.DTO.AuthResponseDTO;
import com.schegolevalex.heat_engineering_calculations.DTO.ReqistrationRequestDTO;
import com.schegolevalex.heat_engineering_calculations.models.User;
import com.schegolevalex.heat_engineering_calculations.security.AccessTokenUtil;
import com.schegolevalex.heat_engineering_calculations.security.RefreshTokenUtil;
import com.schegolevalex.heat_engineering_calculations.security.UserDetailsImpl;
import com.schegolevalex.heat_engineering_calculations.security.exceptions.RefreshTokenVerificationException;
import com.schegolevalex.heat_engineering_calculations.security.exceptions.UserRegistrationException;
import com.schegolevalex.heat_engineering_calculations.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    final UserService userService;
    final AccessTokenUtil accessTokenUtil;
    final RefreshTokenUtil refreshTokenUtil;
    final ModelMapper modelMapper;
    final AuthenticationManager authManager;

    public AuthController(ModelMapper modelMapper,
                          AccessTokenUtil accessTokenUtil,
                          UserService userService,
                          RefreshTokenUtil refreshTokenUtil,
                          AuthenticationManager authManager) {
        this.modelMapper = modelMapper;
        this.accessTokenUtil = accessTokenUtil;
        this.userService = userService;
        this.refreshTokenUtil = refreshTokenUtil;
        this.authManager = authManager;
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthResponseDTO> registration(@RequestBody @Valid ReqistrationRequestDTO reqistrationRequestDTO,
                                                        BindingResult bindingResult,
                                                        HttpServletResponse response) throws UserRegistrationException {
        User user = modelMapper.map(reqistrationRequestDTO, User.class);
        userService.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            throw new UserRegistrationException("Registration exception");

        User registeredUser = userService.register(user);

        String accessToken = accessTokenUtil.generateAccessToken(registeredUser.getUsername(),
                registeredUser.getRoles());

        setRefreshTokenCookie(response, registeredUser);

        return ResponseEntity.ok(new AuthResponseDTO(reqistrationRequestDTO.getUserName(), accessToken));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginProcessing(@RequestBody AuthRequestDTO authDTO,
                                                           HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authInputToken
                = new UsernamePasswordAuthenticationToken(authDTO.getUserName(), authDTO.getPassword());
        Authentication authentication = authManager.authenticate(authInputToken);

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        String accessToken = accessTokenUtil.generateAccessToken(user.getUsername(), authentication.getAuthorities());
        setRefreshTokenCookie(response, user);

        return ResponseEntity.ok(new AuthResponseDTO(user.getUsername(), accessToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("refresh-token"))
                .findFirst()
                .orElseThrow(() -> new RefreshTokenVerificationException("There is no refresh token in your request. Please, log in."));
        User user = refreshTokenUtil.getUserFromRefreshToken(cookie.getValue());

        String accessToken = accessTokenUtil.generateAccessToken(user.getUsername(), user.getRoles());
        setRefreshTokenCookie(response, user);

        System.out.println("**************************");
        System.out.println("были тут");
        System.out.println("**************************");
        return ResponseEntity.ok(new AuthResponseDTO(user.getUsername(), accessToken));
    }

    private void setRefreshTokenCookie(HttpServletResponse response, User user) {
        Cookie cookie = new Cookie("refresh-token", refreshTokenUtil.generateRefreshToken(user).getValue());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        response.addCookie(cookie);
    }
}
