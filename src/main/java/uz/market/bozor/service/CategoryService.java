package uz.market.bozor.service;

import uz.market.bozor.filter.CategoryFilter;
import uz.market.bozor.payload.model.ApiResponse;

import java.util.UUID;

public interface CategoryService {
    ApiResponse deleteCategory(UUID categoryId);

    ApiResponse getCategories(CategoryFilter filter);
}
