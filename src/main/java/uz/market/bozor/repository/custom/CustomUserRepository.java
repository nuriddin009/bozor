package uz.market.bozor.repository.custom;

import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.response.UserResponse;
import uz.market.bozor.repository.page.ResponsePage;

public interface CustomUserRepository {
    ResponsePage<UserResponse> findAllByFilter(UserFilter userFilter);

    ResponsePage<UserResponse> findStoreUsersByFilter(UserFilter filter);
}
