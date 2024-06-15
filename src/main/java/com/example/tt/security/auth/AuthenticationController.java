package com.example.tt.security.auth;

import com.example.tt.security.auth.dto.AuthRequest;
import com.example.tt.security.auth.dto.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthenticationController {

  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
      this.service = service;
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(service.register(request).getUsername());
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
    System.out.println(request);
    return ResponseEntity.ok(service.authenticate(request));
  }
}
