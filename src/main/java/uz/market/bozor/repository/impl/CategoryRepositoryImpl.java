package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import uz.market.bozor.repository.CustomCategoryRepository;

@Repository
public class CategoryRepositoryImpl implements CustomCategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;


}
