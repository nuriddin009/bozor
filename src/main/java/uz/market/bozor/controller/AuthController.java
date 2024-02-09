package uz.market.bozor.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.payload.request.AuthReq;
import uz.market.bozor.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("signIn")
    public ResponseEntity<?> signIn(@RequestBody @Valid AuthReq authReq) {
        return ResponseEntity.ok(authService.signIn(authReq));
    }

    @PostMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        authService.refreshToken(request, response);
    }

}
