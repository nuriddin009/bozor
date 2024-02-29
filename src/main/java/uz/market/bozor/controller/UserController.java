package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.model.BaseResponse;
import uz.market.bozor.service.UserService;

import static uz.market.bozor.component.ResponseGenerator.generateSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;



    @GetMapping
    public HttpEntity<BaseResponse<?>> getUsers(@ParameterObject UserFilter userFilter) {
        return new ResponseEntity<>(service.getUsers(userFilter), HttpStatus.OK);
    }


    @PostMapping
    public HttpEntity<BaseResponse<String>> saveUser() {
        return new ResponseEntity<>(generateSuccessResponse.apply("User saved successfully"), HttpStatus.OK);
    }


}
