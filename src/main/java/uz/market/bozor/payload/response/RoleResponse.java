package uz.market.bozor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.market.bozor.entity.constants.RoleName;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Integer id;
    private RoleName roleName;
}
