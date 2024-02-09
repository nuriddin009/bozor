package uz.market.bozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.bozor.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
