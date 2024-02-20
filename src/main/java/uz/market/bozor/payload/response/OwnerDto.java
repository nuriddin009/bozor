package uz.market.bozor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {

    private String firstname;
    private String lastname;
    private String phoneNumber;

}
