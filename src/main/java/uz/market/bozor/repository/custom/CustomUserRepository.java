package uz.market.bozor.repository.custom;

import org.springframework.data.domain.Page;
import uz.market.bozor.entity.User;
import uz.market.bozor.filter.UserFilter;

public interface CustomUserRepository {
      Page<User> findAllByFilter(UserFilter userFilter);
}
