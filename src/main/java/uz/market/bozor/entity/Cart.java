package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update cart set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@Entity
public class Cart extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    private int quantity;
}
