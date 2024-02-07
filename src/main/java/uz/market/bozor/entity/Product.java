package uz.market.bozor.entity;

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


    private String name;
    private String details;
    private BigDecimal amount = BigDecimal.valueOf(0);

}
