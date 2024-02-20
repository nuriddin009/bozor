package uz.market.bozor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponse {


    private String name;
    private LocationRes location;
    private String supportPhone;
    private String email;
    private String bankAccount;
    private String bankAccountDetails;
    private OwnerDto owner;


}
