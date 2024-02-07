package uz.market.bozor.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
    void updateEntity(D dto, @MappingTarget E entity);
    List<E> toEntityList(List<D> dtos);
    List<D> toDtoList(List<E> entities);
}
