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
@SQLDelete(sql = "update alert_store set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@Entity
public class AlertStore extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Alert alert;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private int showCount;

}
