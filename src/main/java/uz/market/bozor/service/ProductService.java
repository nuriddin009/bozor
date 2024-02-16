package uz.market.bozor.service;

import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.model.ApiResponse;

public interface ProductService {
    ApiResponse getProducts(ProductFilter filter);
}
