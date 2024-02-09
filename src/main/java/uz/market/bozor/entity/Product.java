package uz.market.bozor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update product set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    private BigDecimal price;
    private BigDecimal oldPrice;
    private String itemCode;
    private String name;
    @Column(columnDefinition = "text")
    private String details;
    private BigDecimal amount = BigDecimal.valueOf(0);

}
