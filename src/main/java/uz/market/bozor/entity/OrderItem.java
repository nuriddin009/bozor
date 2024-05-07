package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@SQLDelete(sql = "update order_item set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Table(name = "order_item")
@Entity
public class OrderItem extends BaseEntity {


    private Integer quantity;
    private Integer kg;

}
