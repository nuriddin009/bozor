package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductRating extends BaseEntity {

    @ManyToOne
    private Rating rating;
    @ManyToOne
    private Product product;


}
