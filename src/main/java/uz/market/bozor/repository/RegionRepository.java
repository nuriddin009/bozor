package uz.market.bozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.bozor.entity.Region;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer>, CustomRegionRepository {


}
