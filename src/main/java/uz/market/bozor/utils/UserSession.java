package uz.market.bozor.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.market.bozor.entity.User;
import uz.market.bozor.repository.UserRepository;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserSession {


    private final UserRepository userRepository;


    public String getUsername() {
        return getPrincipal()
                .map(user -> user.getPrincipal().toString())
                .orElse(null);
    }

    public User getUser() {
        return userRepository.findByEmail(getUsername()).orElse(null);
    }

    public Optional<UsernamePasswordAuthenticationToken> getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        return principal instanceof UsernamePasswordAuthenticationToken ? Optional.of((UsernamePasswordAuthenticationToken) principal) : Optional.empty();
    }

}