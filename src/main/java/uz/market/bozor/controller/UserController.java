package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.service.UserService;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;


    @PreAuthorize(value = "hasAnyRole('ROLE_SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse> getUsers(@ParameterObject UserFilter filter) {
        ApiResponse response = service.getUsers(filter);
        return new ResponseEntity<>(response, response.isStatus() ?
                OK : INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/store")
    public ResponseEntity<ApiResponse> getStoreUsers(@ParameterObject UserFilter filter) {
        ApiResponse response = service.getStoreUsers(filter);
        return ResponseEntity.ok(ApiResponse.successResponse(""));
    }


}
