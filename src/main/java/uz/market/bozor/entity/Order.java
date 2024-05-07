package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update orders set deleted=false where id=?")
@SQLRestriction(value = "deleted=false")
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;

    private BigDecimal amount;
}
