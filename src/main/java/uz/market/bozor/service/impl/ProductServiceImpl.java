package uz.market.bozor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.market.bozor.entity.Product;
import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.payload.response.ProductResponse;
import uz.market.bozor.repository.AttachmentRepository;
import uz.market.bozor.repository.ProductRepository;
import uz.market.bozor.repository.page.ResponsePage;
import uz.market.bozor.service.FileUploadService;
import uz.market.bozor.service.ProductService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileUploadService fileUploadService;

    @Transactional(readOnly = true)
    @Override
    public ApiResponse getProducts(ProductFilter filter) {
        ResponsePage<ProductResponse> products = productRepository.findAllByFilter(filter);
        return ApiResponse.successResponse(products, "Product list");
    }

    @Transactional
    @Override
    public ApiResponse deleteProduct(UUID productId) {

        ApiResponse response = new ApiResponse();
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow();
            product.getImages().forEach(image -> {
                fileUploadService.removeObject(image);
                attachmentRepository.delete(image);
            });
            productRepository.delete(product);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Error");
        }

        return response;
    }
}