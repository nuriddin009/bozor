package uz.market.bozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.bozor.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
