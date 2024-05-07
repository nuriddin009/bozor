package uz.market.bozor.repository;

import uz.market.bozor.entity.Category;
import uz.market.bozor.repository.custom.CustomCategoryRepository;

public interface CategoryRepository extends BaseRepository<Category>, CustomCategoryRepository {
}
