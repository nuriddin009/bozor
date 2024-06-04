package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import uz.market.bozor.entity.constants.Language;
import uz.market.bozor.filter.ProductFilter;
import uz.market.bozor.payload.response.ProductResponse;
import uz.market.bozor.repository.custom.ProductCompositeRepository;
import uz.market.bozor.repository.page.RequestPageImpl;
import uz.market.bozor.repository.page.ResponsePage;
import uz.market.bozor.repository.page.ResponsePageImpl;

import java.math.BigDecimal;

@Repository
public class ProductRepositoryImpl implements ProductCompositeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponsePage<ProductResponse> findAllByFilter(ProductFilter filter) {
        final boolean hasSearch = StringUtils.isNotEmpty(filter.getSearch());
        final boolean hasSort = filter.getPageable().getSort().isSorted();

        StringBuilder sqlBegin = new StringBuilder("select ");

        if (filter.getLanguage().equals(Language.eng)) {
            sqlBegin.append(" t.nameEng as name, t.detailsEng as details");
        }else if (filter.getLanguage().equals(Language.ru)) {
            sqlBegin.append(" t.nameRu as name, t.detailsRu as details");
        }else {
            sqlBegin.append(" t.nameUz as name, t.detailsUz as details");
        }

        StringBuilder sql = new StringBuilder(" from Product t ");
        sql.append(" join t.colors");

        sql.append(" where t.deleted=false ");

        if (hasSearch) {
            sql.append(" and (lower(t.nameUz) like :searchKey");
            sql.append(" or lower(t.nameRu) like :searchKey");
            sql.append(" or lower(t.nameEng) like :seasrchKey");
            sql.append(" or lower(t.detailsUz) like :searchKey");
            sql.append(" or lower(t.detailsRu) like :searchKey");
            sql.append(" or lower(t.detailsEng) like :searchKey)");
        }

        if (!ObjectUtils.isEmpty(filter.getMinPrice())
                && filter.getMinPrice().compareTo(BigDecimal.ZERO) > 0)
            sql.append(" and t.price >= :minPrice ");
        else
            sql.append(" and t.price >=0");

        if (!ObjectUtils.isEmpty(filter.getMaxPrice())
                && filter.getMinPrice().compareTo(filter.getMaxPrice()) < 0) {
            sql.append(" and t.price <=:maxPrice ");
        }

        if (!CollectionUtils.isEmpty(filter.getColors())) {
            sql.append(" and t.colors in :targetColors ");
        }

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
            sql.append(" t.id");
        }

        TypedQuery<ProductResponse> query = entityManager.createQuery(sqlBegin + sql.toString(), ProductResponse.class);
        TypedQuery<Long> countQuery = entityManager.createQuery("select count(t.id) " + sql, Long.class);

        if (hasSearch) {
            query.setParameter("searchKey", filter.getSearchForQuery());
            countQuery.setParameter("searchKey", filter.getSearchForQuery());
        }
        if (!ObjectUtils.isEmpty(filter.getMinPrice())) {
            query.setParameter("minPrice", filter.getMinPrice());
            countQuery.setParameter("minPrice", filter.getMinPrice());
        }


        if (!ObjectUtils.isEmpty(filter.getMaxPrice()) && !ObjectUtils.isEmpty(filter.getMinPrice())
                && filter.getMaxPrice().compareTo(filter.getMinPrice()) > 0) {
            query.setParameter("maxPrice", filter.getMaxPrice());
            countQuery.setParameter("maxPrice", filter.getMaxPrice());
        }

        if (!CollectionUtils.isEmpty(filter.getColors())) {
            query.setParameter("targetColors", filter.getColors());
            countQuery.setParameter("targetColors", filter.getColors());
        }

        ResponsePageImpl<ProductResponse> response = new ResponsePageImpl<>();
        response.setTotalElements(countQuery.getSingleResult());
        response.setElements(query.getResultList());
        response.setRequestPage(new RequestPageImpl(filter.getPage(),
                filter.getSize(), filter.getStart()));

        return response;
    }
}
