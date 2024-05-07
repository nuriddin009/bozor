package uz.market.bozor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private BigDecimal price;
    private BigDecimal oldPrice;
    private String name;
    private String details;

}
