package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.repository.DistrictRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/district")
public class DistrictController {

    private final DistrictRepository districtRepository;


    @GetMapping
    public ResponseEntity<ApiResponse> getDistricts(@RequestParam(value = "search", defaultValue = "") String search,
                                                    @RequestParam("regionId") Integer regionId) {
        return ResponseEntity.ok(districtRepository.getDistricts(search, regionId));
    }


}
