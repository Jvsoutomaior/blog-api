package dev.jvsmaior.blog_api.controller;

import dev.jvsmaior.blog_api.dto.security.LoginRequestDTO;
import dev.jvsmaior.blog_api.dto.security.RegistrationRequestDTO;
import dev.jvsmaior.blog_api.entity.AppUser;
import dev.jvsmaior.blog_api.entity.UserRole;
import dev.jvsmaior.blog_api.repository.AppUserRepository;
import dev.jvsmaior.blog_api.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager, AppUserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO data){
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePasswordToken);

        String token = tokenService.generateToken((AppUser) Objects.requireNonNull(auth.getPrincipal()));

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register") //Registration
    public ResponseEntity<Void> registration(@Valid @RequestBody RegistrationRequestDTO data){
        if(repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = passwordEncoder.encode(data.password());
        AppUser newUser = new AppUser(data.username(), encryptedPassword, UserRole.ADMIN);

        this.repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
