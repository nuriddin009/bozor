package uz.market.bozor.service;

import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.model.BaseResponse;

public interface UserService {
    BaseResponse<?> getUsers(UserFilter userFilter);
}
