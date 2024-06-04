package uz.market.bozor.payload.request;

import lombok.Data;
import uz.market.bozor.entity.constants.Language;

@Data
public class RegisterReq {
    private String email;
    private String password;
    private String confirmPassword;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Language language;
}
