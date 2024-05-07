package uz.market.bozor.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.market.bozor.entity.User;
import uz.market.bozor.entity.constants.Status;
import uz.market.bozor.payload.model.UserManager;
import uz.market.bozor.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            if (user.getStatus().equals(Status.ACTIVE))
                return new UserManager(user);
            else
                throw new UserNotActiveException("User status not active");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static class UserNotActiveException extends RuntimeException {
        public UserNotActiveException(String message) {
            super(message);
        }
    }

}