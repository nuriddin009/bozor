package uz.market.bozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.bozor.entity.Role;
import uz.market.bozor.entity.constants.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {


    Role findByRoleName(RoleName roleName);


}
