package uz.market.bozor.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private UUID productId;
    private Integer feedbackQuantity;
    private float rating;
    private Integer ordersQuantity;
    private String name;
    private String details;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private List<Photo> photos;
    private BigDecimal amount;


}
