package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.market.bozor.filter.CategoryFilter;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.service.CategoryService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService service;


    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteCategory(@RequestParam UUID categoryId) {
        ApiResponse response = service.deleteCategory(categoryId);
        return new ResponseEntity<>(response, response.isStatus() ?
                HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCategories(@ParameterObject CategoryFilter filter){
        ApiResponse response = service.getCategories(filter);
        return new ResponseEntity<>(response, response.isStatus() ?
                HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
