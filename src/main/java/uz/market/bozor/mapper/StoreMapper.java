package uz.market.bozor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.market.bozor.entity.Store;
import uz.market.bozor.payload.request.StoreRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StoreMapper extends BaseMapper<Store, StoreRequest> {
}