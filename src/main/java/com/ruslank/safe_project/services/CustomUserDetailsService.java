package com.ruslank.safe_project.services;

import com.ruslank.safe_project.entities.User;
import com.ruslank.safe_project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);

        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username was not found"));
    }
}
