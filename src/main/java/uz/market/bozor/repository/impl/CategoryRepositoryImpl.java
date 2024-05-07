package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import uz.market.bozor.entity.Category;
import uz.market.bozor.entity.constants.Language;
import uz.market.bozor.filter.CategoryFilter;
import uz.market.bozor.payload.response.CategoryResponse;
import uz.market.bozor.repository.custom.CustomCategoryRepository;
import uz.market.bozor.repository.page.RequestPageImpl;
import uz.market.bozor.repository.page.ResponsePage;
import uz.market.bozor.repository.page.ResponsePageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements CustomCategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponsePage<CategoryResponse> findAllByFilter(CategoryFilter filter) {
        final boolean hasSearch = StringUtils.isNotEmpty(filter.getSearch());
        final boolean hasSort = filter.getPageable().getSort().isSorted();

        StringBuilder sql = new StringBuilder("select t from Category t where t.parent is null ");


        TypedQuery<Category> query = entityManager.createQuery(sql.toString(), Category.class);
        String selectT = sql.toString().replace("select t", "select count(t)");
        TypedQuery<Long> countQuery = entityManager.createQuery(selectT, Long.class);
        List<Category> categories = query.getResultList();

        List<CategoryResponse> collect = categories.stream()
                .map(category -> this.mapCategoryToResponse(category, filter.getLanguage()))
                .toList();

        ResponsePageImpl<CategoryResponse> response = new ResponsePageImpl<>();
        response.setTotalElements(countQuery.getSingleResult());
        response.setElements(collect);
        response.setRequestPage(new RequestPageImpl(filter.getPage(), filter.getSize(), filter.getStart()));

        return response;
    }


    private CategoryResponse mapCategoryToResponse(Category category, Language language) {
        CategoryResponse response = new CategoryResponse();

        switch (language) {
            case ru -> {
                response.setName(category.getNameRu());
                response.setDetails(category.getDetailsRu());
            }
            case eng -> {
                response.setName(category.getNameEng());
                response.setDetails(category.getDetailsEng());
            }
            default -> {
                response.setName(category.getNameUz());
                response.setDetails(category.getDetailsUz());
            }
        }

        if (category.getImage() != null) {
            response.setImage(category.getImage().getUrl());
        }
        List<Category> children = getChildren(category);
        if (!children.isEmpty()) {
            List<CategoryResponse> childrenResponse = children.stream()
                    .map(category1 -> mapCategoryToResponse(category1, language))
                    .collect(Collectors.toList());
            response.setChildren(childrenResponse);
        }
        return response;
    }

    private List<Category> getChildren(Category parent) {
        String jpql = "SELECT c FROM Category c WHERE c.parent = :parent";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("parent", parent);
        return query.getResultList();
    }

}
