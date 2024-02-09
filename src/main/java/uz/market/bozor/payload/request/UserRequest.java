package uz.market.bozor.payload.request;

import lombok.Data;
import uz.market.bozor.entity.Privilege;
import uz.market.bozor.entity.Role;
import uz.market.bozor.entity.constants.Status;

import java.util.Set;

@Data
public class UserRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Status status;
    private Set<Role> roles;
    private Set<Privilege> privileges;

}
