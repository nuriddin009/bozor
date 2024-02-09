package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import uz.market.bozor.entity.User;
import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.repository.CustomUserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> findAllByFilter(UserFilter userFilter) {

        
        return null;
    }
}
