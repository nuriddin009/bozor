package uz.market.bozor.repository;

import uz.market.bozor.entity.Product;
import uz.market.bozor.repository.custom.CustomProductRepository;

public interface ProductRepository extends BaseRepository<Product>, CustomProductRepository {
}
