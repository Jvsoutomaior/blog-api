package dev.jvsmaior.blog_api.repository;

import dev.jvsmaior.blog_api.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    UserDetails findByUsername(String username);
}
