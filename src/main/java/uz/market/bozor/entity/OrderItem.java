package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update order_item set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@Entity
public class OrderItem extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne
    private Product product;

    @Min(value = 1, message = "Quantity must be at last 1")
    private int quantity;


}
