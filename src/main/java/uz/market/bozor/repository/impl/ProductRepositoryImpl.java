package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import uz.market.bozor.repository.CustomProductRepository;

@Repository
public class ProductRepositoryImpl implements CustomProductRepository {
    @PersistenceContext
    private EntityManager entityManager;


}
