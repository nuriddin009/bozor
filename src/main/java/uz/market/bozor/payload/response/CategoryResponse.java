package uz.market.bozor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String name;
    private String details;
    private String image;
    private List<CategoryResponse> children;
}