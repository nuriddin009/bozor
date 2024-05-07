package uz.market.bozor.payload.response;

import lombok.Builder;
import lombok.Data;
import uz.market.bozor.entity.constants.Status;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private Status status;
    private List<RoleResponse> roles;
}
