package uz.market.bozor.service;

import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.model.ApiResponse;

public interface UserService {

    ApiResponse getUsers(UserFilter filter);

    ApiResponse getStoreUsers(UserFilter filter);
}
