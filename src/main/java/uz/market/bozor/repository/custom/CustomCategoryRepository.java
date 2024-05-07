package uz.market.bozor.repository.custom;

import uz.market.bozor.filter.CategoryFilter;
import uz.market.bozor.payload.response.CategoryResponse;
import uz.market.bozor.repository.page.ResponsePage;

public interface CustomCategoryRepository {
    ResponsePage<CategoryResponse> findAllByFilter(CategoryFilter filter);
}
