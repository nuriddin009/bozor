package uz.market.bozor.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.market.bozor.payload.model.ApiResponse;
import uz.market.bozor.repository.RegionRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/region")
public class RegionController {

    private final RegionRepository regionRepository;


    @GetMapping
    public ResponseEntity<ApiResponse> getRegions(@RequestParam(value = "search", defaultValue = "") String search) {
        return ResponseEntity.ok(regionRepository.getRegions(search));
    }


}
