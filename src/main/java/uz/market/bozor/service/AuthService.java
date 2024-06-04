package uz.market.bozor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.market.bozor.entity.Role;
import uz.market.bozor.entity.User;
import uz.market.bozor.entity.constants.RoleName;
import uz.market.bozor.entity.constants.Status;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.payload.model.UserManager;
import uz.market.bozor.payload.request.AuthReq;
import uz.market.bozor.payload.request.RegisterReq;
import uz.market.bozor.payload.response.JwtResponse;
import uz.market.bozor.payload.response.RoleResponse;
import uz.market.bozor.payload.response.UserResponse;
import uz.market.bozor.repository.RoleRepository;
import uz.market.bozor.repository.UserRepository;
import uz.market.bozor.security.JwtService;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final RoleRepository roleRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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


    public ApiResponse signUp(RegisterReq req) {

        ApiResponse response = new ApiResponse();
        try {
            if (userRepository.existsByEmail(req.getEmail()))
                throw new EntityExistsException(req.getEmail() + " already registered");

            if (!req.getPassword().equals(req.getConfirmPassword()))
                throw new RuntimeException("Password not matched");

            Role roleUser = roleRepository.findByRoleName(RoleName.ROLE_USER);

            User user = User.builder()
                    .firstname(req.getFirstname())
                    .lastname(req.getLastname())
                    .email(req.getEmail())
                    .phoneNumber(req.getPhoneNumber())
                    .status(Status.CONFIRM)
                    .password(passwordEncoder.encode(req.getPassword()))
                    .roles(Set.of(roleUser))
                    .build();

            userRepository.save(user);
            response.setMessage("You have registered successfully");
            response.setData(UserResponse.builder()
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .roles(user.getRoles().stream().map(role -> new RoleResponse(role.getId(), role.getRoleName())).toList())
                    .status(user.getStatus())
                    .build());
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
