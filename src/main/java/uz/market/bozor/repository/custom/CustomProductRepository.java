package uz.market.bozor.repository.custom;

import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.response.ProductResponse;

import java.util.List;

public interface CustomProductRepository {


    List<ProductResponse> getProducts(ProductFilter filter);


}
