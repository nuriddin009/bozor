package uz.market.bozor.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link uz.market.bozor.entity.Store}
 */

public record StoreRequest(String name, @NotNull String supportPhone, double latitude, double longitude,
                           @Email String email, @NotNull String bankAccount,
                           String bankAccountDetails) implements Serializable {
}