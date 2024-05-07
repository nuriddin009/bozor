package uz.market.bozor.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.market.bozor.entity.Attachment;
import uz.market.bozor.entity.Category;
import uz.market.bozor.filter.CategoryFilter;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.payload.response.CategoryResponse;
import uz.market.bozor.repository.AttachmentRepository;
import uz.market.bozor.repository.CategoryRepository;
import uz.market.bozor.repository.page.ResponsePage;
import uz.market.bozor.service.CategoryService;
import uz.market.bozor.service.FileUploadService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileUploadService fileUploadService;


    @Transactional
    @Override
    public ApiResponse deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Attachment image = category.getImage();
        fileUploadService.removeObject(image);
        categoryRepository.delete(category);
        attachmentRepository.delete(image);
        return ApiResponse.successResponse("Category deleted");
    }

    @Override
    public ApiResponse getCategories(CategoryFilter filter) {
        ResponsePage<CategoryResponse> allByFilter = categoryRepository.findAllByFilter(filter);
        return ApiResponse.successResponse(allByFilter, "Categories");
    }
}
