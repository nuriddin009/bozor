package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.service.ProductService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService service;


    @GetMapping
    public HttpEntity<ApiResponse> getProducts(@ParameterObject ProductFilter filter) {
        ApiResponse response = service.getProducts(filter);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteProduct(@RequestParam UUID productId) {
       ApiResponse response = service.deleteProduct(productId);
        return new ResponseEntity<>(response, response.isStatus() ?
                HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
