package uz.market.bozor.repository.custom;

import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.response.ProductResponse;
import uz.market.bozor.repository.page.ResponsePage;

public interface ProductCompositeRepository {
    ResponsePage<ProductResponse> findAllByFilter(ProductFilter filter);
}
