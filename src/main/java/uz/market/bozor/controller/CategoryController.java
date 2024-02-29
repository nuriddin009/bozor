package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.bozor.payload.model.BaseResponse;

import static uz.market.bozor.component.ResponseGenerator.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {


    @GetMapping
    public HttpEntity<BaseResponse<?>> getCategories() {
        BaseResponse<String> response = generateFailedResponse.apply("Category list");
        return ResponseEntity.ok(response);
    }

}
