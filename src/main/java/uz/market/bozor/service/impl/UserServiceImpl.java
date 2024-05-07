package uz.market.bozor.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.market.bozor.entity.User;
import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.payload.model.BaseResponse;
import uz.market.bozor.payload.request.UserRequest;
import uz.market.bozor.payload.response.UserResponse;
import uz.market.bozor.repository.UserRepository;
import uz.market.bozor.repository.page.ResponsePage;
import uz.market.bozor.service.UserService;
import uz.market.bozor.utils.UserSession;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSession userSession;


    @Override
    public ApiResponse getUsers(UserFilter filter) {
        ResponsePage<UserResponse> allByFilter = userRepository.findAllByFilter(filter);
        return ApiResponse.successResponse(allByFilter, "Users list");
    }

    @Override
    public ApiResponse getStoreUsers(UserFilter filter) {
        User user = userSession.getUser();


        return ApiResponse.successResponse("");
    }
}
