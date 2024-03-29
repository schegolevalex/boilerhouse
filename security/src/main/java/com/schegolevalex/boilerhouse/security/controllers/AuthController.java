package com.schegolevalex.boilerhouse.security.controllers;

import com.schegolevalex.boilerhouse.security.DTO.AuthRequestDTO;
import com.schegolevalex.boilerhouse.security.DTO.AuthResponseDTO;
import com.schegolevalex.boilerhouse.security.DTO.ReqistrationRequestDTO;
import com.schegolevalex.boilerhouse.security.exceptions.RefreshTokenVerificationException;
import com.schegolevalex.boilerhouse.security.exceptions.UserRegistrationException;
import com.schegolevalex.boilerhouse.security.models.User;
import com.schegolevalex.boilerhouse.security.services.UserDetailsImpl;
import com.schegolevalex.boilerhouse.security.services.UserService;
import com.schegolevalex.boilerhouse.security.utils.AccessTokenUtil;
import com.schegolevalex.boilerhouse.security.utils.RefreshTokenUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

        // TODO при регистрации я УЖЕ создаю refreshToken, а потом в методе ниже создаю его еще раз...
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

    @GetMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("refresh-token"))
                .findFirst()
                .orElseThrow(() -> new RefreshTokenVerificationException("There is no refresh token in your request. Please, log in."));
        User user = refreshTokenUtil.getUserFromRefreshToken(cookie.getValue());

        String accessToken = accessTokenUtil.generateAccessToken(user.getUsername(), user.getRoles());
        setRefreshTokenCookie(response, user);

        return ResponseEntity.ok(new AuthResponseDTO(user.getUsername(), accessToken));
    }

    private void setRefreshTokenCookie(HttpServletResponse response, User user) {
        Cookie cookie = new Cookie("refresh-token", refreshTokenUtil.generateRefreshToken(user).getValue());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        response.addCookie(cookie);
    }
}
