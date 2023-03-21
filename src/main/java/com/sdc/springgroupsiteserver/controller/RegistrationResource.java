package com.sdc.springgroupsiteserver.controller;

import com.sdc.springgroupsiteserver.dto.UserCredentialsDto;
import com.sdc.springgroupsiteserver.entities.Role;
import com.sdc.springgroupsiteserver.entities.User;
import com.sdc.springgroupsiteserver.repositories.ProjectRepository;
import com.sdc.springgroupsiteserver.repositories.UserRepository;
import com.sdc.springgroupsiteserver.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
@RequiredArgsConstructor
class RegistrationResource {

    @Autowired
    private UserRepository userRepository;
    private static int strength = 10;
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());

    private final JwtService jwtService;

    @PostMapping("/api/auth/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String register(@RequestBody UserCredentialsDto userCredentialsDto) {
        String encodedPassword = passwordEncoder.encode(userCredentialsDto.getPassword());
        User user = User.builder().build();
        user.setUserName(userCredentialsDto.getUserName());
        user.setPassword(encodedPassword);
        user.setEmail(userCredentialsDto.getEmail());
        user.setRole(Role.USER);
        userRepository.save(user);
        return jwtService.generateToken(user);
    }
}