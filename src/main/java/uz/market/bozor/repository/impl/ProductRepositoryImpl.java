package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.response.ProductResponse;
import uz.market.bozor.repository.CustomProductRepository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements CustomProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductResponse> getProducts(ProductFilter filter) {
        final boolean hasSearch = StringUtils.isNotEmpty(filter.getSearch());
        final boolean hasSort = filter.formPageable().getSort().isSorted();

        StringBuilder sql = new StringBuilder("select new uz.market.bozor.payload.response.ProductResponse(null, null, 3.2f, null, null, null, null, null, null, null) " +
                "from Product t ");

        TypedQuery<ProductResponse> query = entityManager.createQuery(sql.toString(), ProductResponse.class);
        return query.getResultList();
    }
}
