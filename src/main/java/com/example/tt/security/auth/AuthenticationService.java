package com.example.tt.security.auth;


import com.example.tt.exceptions.UsernameIsAlreadyExist;
import com.example.tt.models.entities.User;
import com.example.tt.models.enums.Role;
import com.example.tt.repositories.UserRepository;
import com.example.tt.security.auth.dto.AuthRequest;
import com.example.tt.security.auth.dto.AuthResponse;
import com.example.tt.security.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public User register(AuthRequest request) {
        Optional<User> optionalUser = userRepository.findByUserName(request.getLogin());
        if (optionalUser.isPresent()) {
            throw new UsernameIsAlreadyExist("Username \"" + request.getLogin() + "\" already exists.");
        }

        User user = new User(
                request.getLogin(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER
        );
        return userRepository.save(user);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        String username = request.getLogin();

        var user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username - " + username + " is not found."));

        var jwtToken = jwtService.generateToken(user);
        jwtService.revokeAllUserTokens(user);
        jwtService.saveUserToken(user, jwtToken);

        return new AuthResponse(jwtToken);
    }

}
