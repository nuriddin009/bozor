package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.bozor.entity.constants.Response;
import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.model.BaseResponse;
import uz.market.bozor.service.UserService;

import java.util.function.Function;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;

    private final Function<String, BaseResponse<String>> generateSuccessResponse = (msg -> {
        BaseResponse<String> response = new BaseResponse<>();
        response.setMessage(Response.BaseResponseMessage.SUCCESS.toString());
        response.setResponseData(msg);
        return response;
    });

    private final Function<String, BaseResponse<String>> generateFailedResponse = (msg -> {
        BaseResponse<String> response = new BaseResponse<>();
        response.setMessage(Response.BaseResponseMessage.FAILED.toString());
        response.setResponseData(msg);
        return response;
    });


    @GetMapping
    public HttpEntity<BaseResponse<?>> getUsers(@ParameterObject UserFilter userFilter) {
        return new ResponseEntity<>(service.getUsers(userFilter), HttpStatus.OK);
    }


}
