package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import uz.market.bozor.repository.custom.CustomStoreRepository;

@Repository
public class StoreRepositoryImpl implements CustomStoreRepository {

    @PersistenceContext
    private EntityManager entityManager;

}
