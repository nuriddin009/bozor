package uz.market.bozor.repository;

import uz.market.bozor.entity.User;
import uz.market.bozor.filter.UserFilter;

import java.util.List;

public interface CustomUserRepository {
      List<User> findAllByFilter(UserFilter userFilter);
}
