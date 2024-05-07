package uz.market.bozor.service;

import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.model.ApiResponse;

import java.util.UUID;

public interface ProductService {
    ApiResponse getProducts(ProductFilter filter);

    ApiResponse deleteProduct(UUID productId);
}
