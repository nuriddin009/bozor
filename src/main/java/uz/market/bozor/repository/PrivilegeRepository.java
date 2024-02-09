package uz.market.bozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.market.bozor.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
}