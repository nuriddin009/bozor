package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import uz.market.bozor.entity.Region;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.repository.CustomRegionRepository;

import java.util.List;

@Repository
public class RegionRepositoryImpl implements CustomRegionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ApiResponse getRegions(String search) {
        final boolean hasSearch = StringUtils.isNoneBlank(search);

        StringBuilder sql = new StringBuilder("select t from Region t ");

        if (hasSearch) {
            sql.append(" where (upper(t.nameUz) like upper(:searchKey)");
            sql.append(" or (upper(t.nameRu) like upper(:searchKey) ");
            sql.append(" or (upper(t.nameOz) like upper(:searchKey))");
        }

        TypedQuery<Region> query = entityManager.createQuery(sql.toString(), Region.class);

        if (hasSearch) {
            query.setParameter("searchKey", search);
        }

        return new ApiResponse(query.getResultList(), "Regions list", true);
    }
}
