package uz.market.bozor.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.market.bozor.entity.User;
import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.mapper.UserMapper;
import uz.market.bozor.payload.model.BaseResponse;
import uz.market.bozor.payload.request.UserRequest;
import uz.market.bozor.repository.UserRepository;
import uz.market.bozor.service.UserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public BaseResponse<?> getUsers(UserFilter userFilter) {
        BaseResponse<List<UserRequest>> response = new BaseResponse<>();
        Page<User> allByFilter = userRepository.findAllByFilter(userFilter);
        List<UserRequest> dtoList = userMapper.toDtoList(allByFilter.getContent());
        response.setResponseData(dtoList);
        response.setMessage("Users list");
        return response;
    }
}
