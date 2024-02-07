package uz.market.bozor.mapper;

import org.mapstruct.Mapper;
import uz.market.bozor.entity.User;
import uz.market.bozor.payload.request.UserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserRequest> {

}
