package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.response.ProductResponse;
import uz.market.bozor.repository.custom.CustomProductRepository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements CustomProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductResponse> getProducts(ProductFilter filter) {
        final boolean hasSearch = StringUtils.isNotEmpty(filter.getSearch());
        final boolean hasSort = filter.formPageable().getSort().isSorted();

        StringBuilder sql = new StringBuilder("select t from Product t where t.deleted is false");

        if (!filter.getColors().isEmpty()) {
            sql.append(" and t.color in :colors ");
        }
        if (!filter.getCategoryIds().isEmpty()) {
            sql.append(" and t.category.id in :categoryIds ");
        }
        if (hasSearch) {
            sql.append(" and (upper(t.nameUz) like upper(:searchKey) or ");
            sql.append(" upper(t.nameRu) like upper(:searchKey) or");
            sql.append(" upper(t.nameEng) like upper(:searchKey) or ");
            sql.append(" upper(t.detailsUz) like upper(:searchKey) or ");
            sql.append(" upper(t.detailsRu) like upper(:searchKey) or ");
            sql.append(" upper(t.detailsEng) like upper(:searchKey)) ");
        }

        sql.append(" order by ");
        String countSql = sql.toString().replaceFirst("select t", "select count(t)");

        if (hasSort) {
            for (Sort.Order order : filter.formPageable().getSort()) {
                sql.append("t.").append(order.getProperty());
                if (order.isDescending()) {
                    sql.append(" desc");
                }
                sql.append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
        }else {
            sql.append(" t.createdAt");
        }

        TypedQuery<ProductResponse> query = entityManager.createQuery(sql.toString(), ProductResponse.class)
                .setFirstResult(filter.getStart()).setMaxResults(filter.getSize());

        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        if (!filter.getColors().isEmpty()) {
            query.setParameter("colors", filter.getColors());
            countQuery.setParameter("colors", filter.getColors());
        }
        if (!filter.getCategoryIds().isEmpty()) {
            query.setParameter("categoryIds", filter.getCategoryIds());
            countQuery.setParameter("categoryIds", filter.getCategoryIds());
        }

        if (hasSearch) {
            query.setParameter("searchKey", filter.getSearchForQuery());
            countQuery.setParameter("searchKey", filter.getSearchForQuery());
        }

        return query.getResultList();
    }
}
