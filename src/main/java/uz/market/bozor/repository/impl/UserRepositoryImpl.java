package uz.market.bozor.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import uz.market.bozor.entity.User;
import uz.market.bozor.filter.UserFilter;
import uz.market.bozor.payload.response.RoleResponse;
import uz.market.bozor.payload.response.UserResponse;
import uz.market.bozor.repository.custom.CustomUserRepository;
import uz.market.bozor.repository.page.RequestPageImpl;
import uz.market.bozor.repository.page.ResponsePage;
import uz.market.bozor.repository.page.ResponsePageImpl;

import java.util.List;

@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ResponsePage<UserResponse> findAllByFilter(UserFilter filter) {
        final boolean hasSearch = StringUtils.isNotEmpty(filter.getSearch());
        final boolean hasSort = filter.formPageable().getSort().isSorted();
        StringBuilder sql = new StringBuilder("select t from User t where t.deleted is false ");

        if (hasSearch) {
            sql.append("and (lower(t.firstname) like :searchKey ");
            sql.append("or lower(t.lastname like :searchKey) ");
        }

        String countSql = sql.toString().replaceFirst("select t", "select count(t)");
        sql.append(" order by ");

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

        TypedQuery<User> query = entityManager.createQuery(sql.toString(), User.class)
                .setFirstResult(filter.getStart()).setMaxResults(filter.getSize());

        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        if (hasSearch) {
            query.setParameter("searchKey", filter.getSearchForQuery());
            countQuery.setParameter("searchKey", filter.getSearchForQuery());
        }

        List<UserResponse> list = query.getResultList().stream()
                .map(user -> UserResponse.builder()
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .roles(user.getRoles().stream()
                                .map(role -> new RoleResponse(role.getId(), role.getRoleName())).toList())
                        .phoneNumber(user.getPhoneNumber())
                        .status(user.getStatus())
                        .email(user.getEmail())
                        .build()).toList();


        ResponsePageImpl<UserResponse> response = new ResponsePageImpl<>();
        response.setTotalElements(countQuery.getSingleResult());
        response.setRequestPage(new RequestPageImpl(filter.getPage(), filter.getSize(), filter.getStart()));
        response.setElements(list);
        return response;
    }
}
