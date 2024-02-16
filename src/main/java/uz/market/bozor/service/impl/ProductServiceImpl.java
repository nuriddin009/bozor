package uz.market.bozor.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.repository.ProductRepository;
import uz.market.bozor.service.ProductService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public ApiResponse getProducts(ProductFilter filter) {
        return ApiResponse.successResponse(productRepository.getProducts(filter), "Products");
    }
}
