package uz.market.bozor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import uz.market.bozor.entity.User;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.payload.model.UserManager;
import uz.market.bozor.payload.request.AuthReq;
import uz.market.bozor.payload.response.JwtResponse;
import uz.market.bozor.repository.UserRepository;
import uz.market.bozor.security.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public JwtResponse signIn(@NotNull AuthReq authReq) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authReq.email(), authReq.password()));
        User user = userRepository.findByEmail(authReq.email()).orElseThrow();
        String accessToken = jwtService.generateToken(new UserManager(user));
        String refreshToken = jwtService.generateRefreshToken(new UserManager(user));
        return new JwtResponse(accessToken, refreshToken);
    }

    @SneakyThrows
    public void refreshToken(@NotNull HttpServletRequest request, HttpServletResponse response) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            UserManager userManager = new UserManager(user);
            if (jwtService.isTokenValid(refreshToken, userManager)) {
                var accessToken = jwtService.generateToken(userManager);

                var authResponse = JwtResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }


    }


}
