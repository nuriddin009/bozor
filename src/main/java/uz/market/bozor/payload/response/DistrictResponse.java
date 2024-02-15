package uz.market.bozor.payload.response;

public record DistrictResponse(
        Integer id,
        String nameUz,
        String nameOz,
        String nameRu) {
}
