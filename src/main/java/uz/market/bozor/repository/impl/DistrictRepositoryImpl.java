package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import uz.market.bozor.entity.District;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.repository.CustomDistrictRepository;

@Repository
public class DistrictRepositoryImpl implements CustomDistrictRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ApiResponse getDistricts(String search, Integer regionId) {
        final boolean hasSearch = StringUtils.isNoneBlank(search);

        StringBuilder sql = new StringBuilder("select t from District t where t.region.id=:regionId");

        if (hasSearch) {
            sql.append(" and (upper(t.nameUz) like upper(:searchKey)");
            sql.append(" or (upper(t.nameRu) like upper(:searchKey) ");
            sql.append(" or (upper(t.nameOz) like upper(:searchKey))");
        }

        TypedQuery<District> query = entityManager.createQuery(sql.toString(), District.class);

        query.setParameter("regionId", regionId);

        if (hasSearch) {
            query.setParameter("searchKey", search);
        }

        return new ApiResponse(query.getResultList(), "Districts list", true);
    }
}
