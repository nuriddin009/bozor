package uz.market.bozor.repository;

import uz.market.bozor.payload.model.ApiResponse;

public interface CustomRegionRepository {
    ApiResponse getRegions(String search);
}
