package uz.market.bozor.repository;

import uz.market.bozor.payload.model.ApiResponse;

public interface CustomDistrictRepository {
    ApiResponse getDistricts(String search,Integer regionId);
}
