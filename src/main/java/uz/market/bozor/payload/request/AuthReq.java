package uz.market.bozor.payload.request;

import jakarta.validation.constraints.NotBlank;

public record AuthReq(
        @NotBlank String email,
        @NotBlank String password
) {
}
