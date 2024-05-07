package uz.market.bozor.repository;

import uz.market.bozor.entity.Product;
import uz.market.bozor.repository.custom.ProductCompositeRepository;

public interface ProductRepository extends BaseRepository<Product>, ProductCompositeRepository {
}
