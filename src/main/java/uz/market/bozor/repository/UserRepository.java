package uz.market.bozor.repository;

import uz.market.bozor.entity.User;
import uz.market.bozor.repository.custom.CustomUserRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User>, CustomUserRepository {

    Optional<User> findByEmail(String email);
}
