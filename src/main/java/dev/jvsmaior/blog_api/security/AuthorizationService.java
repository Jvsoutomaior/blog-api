package dev.jvsmaior.blog_api.security;

import dev.jvsmaior.blog_api.repository.AppUserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    AppUserRepository repository;

    public AuthorizationService(AppUserRepository repository){
        this.repository = repository;
    }

    @Override @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return Optional.ofNullable(repository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
