package com.xivs.weblab44.controller;

import com.xivs.weblab44.dto.AuthenticationRequestDto;
import com.xivs.weblab44.model.User;
import com.xivs.weblab44.repository.PointRepository;
import com.xivs.weblab44.repository.UserRepository;
import com.xivs.weblab44.sequrity.jwt.JwtTokenProvider;
import com.xivs.weblab44.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping("register")
    private ResponseEntity<?> register(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        User user = new User();
        if(authenticationRequestDto.getLogin() == null || authenticationRequestDto.getPassword() == null)
            return ResponseEntity.badRequest().body("bad request");
        user.setLogin(authenticationRequestDto.getLogin());
        user.setPassword(authenticationRequestDto.getPassword());
        if (!userService.register(user)) return ResponseEntity.badRequest().body("User already registered");
        return ResponseEntity.ok().body("Registered");
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        if(authenticationRequestDto.getLogin() == null || authenticationRequestDto.getPassword() == null)
            return ResponseEntity.badRequest().body("bad request");
        try {

            String login = authenticationRequestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, authenticationRequestDto.getPassword()));
            User user = userService.findByLogin(login);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + login + " not found");
            }

            String token = jwtTokenProvider.createToken(login);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", login);
            response.put("token", token);

            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException ex){
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
}
